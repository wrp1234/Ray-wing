package com.wrp.core.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 抽象集合的类型转换器
 * @author wrp
 * @since 2025年07月04日 21:22
 */
public abstract class AbstractListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    private static final PGobject jsonObject = new PGobject();

    private final ObjectMapper objectMapper;

    public AbstractListTypeHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        jsonObject.setType("json");
        try {
            jsonObject.setValue(objectMapper.writeValueAsString(parameter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ps.setObject(i, jsonObject);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toBean(rs.getString(columnName));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toBean(rs.getString(columnIndex));
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toBean(cs.getString(columnIndex));
    }

    private List<T> toBean(String data) {
        if (StringUtils.hasText(data)) {
            try {
                return objectMapper.readValue(data, specificType());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 具体类型，由子类提供
     *
     * @return 具体类型
     */
    protected abstract TypeReference<List<T>> specificType();
}
