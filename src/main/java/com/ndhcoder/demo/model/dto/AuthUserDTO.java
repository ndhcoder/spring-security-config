package com.ndhcoder.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDTO extends UserDTO implements UserDetails {

    public static final int USER_ACTIVE = 0;
    public static final int USER_BLOCK = 10;

    public AuthUserDTO(UserDTO user) {
        super(user);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission ->
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));
        });

        return grantedAuthorities;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return super.getStatus() == USER_ACTIVE;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return super.getStatus() != USER_BLOCK;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return super.getStatus() !=USER_BLOCK;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return super.getStatus() == USER_ACTIVE;
    }
}
