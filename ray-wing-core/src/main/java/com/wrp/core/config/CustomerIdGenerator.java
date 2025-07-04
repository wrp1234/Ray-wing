package com.wrp.core.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * 自定义id生成策略，解决默认雪花id过长导致的js精度丢失
 * @author wrp
 * @since 2025年06月12日 9:17
 **/
@Component
public class CustomerIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return IdGenerator.generateId();
    }

    /**
     * ID结构：53位ID = 40位时间戳 + 5位机器ID + 8位序列号
     */
    public static class IdGenerator {

        private static IdGenerator instance = new IdGenerator(0);

        public static IdGenerator initDefaultInstance(int machineId) {
            instance = new IdGenerator(machineId);
            return instance;
        }

        public static long generateId() {
            return instance.nextId();
        }

        // total bits=53(max 2^53-1：9007199254740992-1)

        // private final static long TIME_BIT = 40; // max: 2318-06-04
        private final static long MACHINE_BIT = 5; // max 31
        private final static long SEQUENCE_BIT = 8; // 256/10ms

        /**
         * mask/max value
         */
        private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
        private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

        private final static long MACHINE_LEFT = SEQUENCE_BIT;
        private final static long TIMESTMP_LEFT = MACHINE_BIT + SEQUENCE_BIT;

        private final long machineId;
        private long sequence = 0L;
        private long lastStmp = -1L;

        private IdGenerator(long machineId) {
            if (machineId > MAX_MACHINE_NUM || machineId < 0) {
                throw new IllegalArgumentException(
                        "machineId can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
            }
            this.machineId = machineId;
        }

        /**
         * generate new ID
         */
        public synchronized long nextId() {
            // 获取当前时间戳(10ms单位)
            long currStmp = getTimestamp();
            // 时钟回拨检查
            if (currStmp < lastStmp) {
                throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
            }

            // 同一时间窗口
            if (currStmp == lastStmp) {
                // 序列号自增
                sequence = (sequence + 1) & MAX_SEQUENCE;
                // 当前窗口序列号用完
                if (sequence == 0L) {
                    // 等待下一时间窗口
                    currStmp = getNextTimestamp();
                }
            } else {
                // 新时间窗口,// 重置序列号
                sequence = 0L;
            }

            // 更新最后时间戳
            lastStmp = currStmp;

            // 组合ID: 时间戳 | 机器ID | 序列号
            return currStmp << TIMESTMP_LEFT //
                    | machineId << MACHINE_LEFT //
                    | sequence;
        }

        private long getNextTimestamp() {
            long mill = getTimestamp();
            while (mill <= lastStmp) {
                mill = getTimestamp();
            }
            return mill;
        }

        private long getTimestamp() {
            // per 10ms
            return System.currentTimeMillis() / 10;// 10ms
        }
    }
}
