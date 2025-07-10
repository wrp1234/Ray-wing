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
    Collection<? extends GrantedAuthority> authorities;

    public User(SysUserEntity user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
