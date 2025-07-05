package com.wrp.user.to;

import com.wrp.user.entity.SysUserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author wrp
 * @since 2025/7/5 18:28
 **/
@Getter
public class User implements UserDetails {

    private final SysUserEntity user;

    public User(SysUserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
