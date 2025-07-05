package com.wrp.user.security;

import com.wrp.user.dict.UserStatus;
import com.wrp.user.entity.SysUserEntity;
import com.wrp.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * 参考{@link org.springframework.security.provisioning.InMemoryUserDetailsManager}
 * @author wrp
 * @since 2025/7/5 21:53
 **/
@Component
@RequiredArgsConstructor
public class DbUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    private final SysUserService sysUserService;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return user;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 从数据库中获取用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user = sysUserService.getByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<? extends GrantedAuthority> authorities = new HashSet<>();
        // 使用security自带的
        boolean enabled = UserStatus.ACTIVE.equals(user.getStatus());
        return new User(user.getUsername(),
                user.getPassword(),
                // 用户是否可用
                enabled,
                // 用户未过期
                true,
                // 凭证未过期
                true,
                // 账户未锁定
                enabled,
                authorities);
    }
}
