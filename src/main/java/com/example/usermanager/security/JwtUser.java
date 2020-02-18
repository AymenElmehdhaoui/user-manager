package com.example.usermanager.security;

import com.example.usermanager.entities.Group;
import com.example.usermanager.entities.Permission;
import com.example.usermanager.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {
    private User user;

    JwtUser() {
        this.user = new User();
    }

    JwtUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Group group = this.user.getGroup();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (group != null) {
            List<Permission> permissions = group.getPermissions();
            permissions.stream().forEach(per -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(per.getLabel()));
            });
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
