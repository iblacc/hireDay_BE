package com.decagonhq.hireday.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.decagonhq.hireday.security.UserPermission.*;

public enum UserRole {
    DECADEV(Sets.newHashSet(DECADEV_READ, DECADEV_WRITE)),
    ADMIN(Sets.newHashSet(DECADEV_READ, EMPLOYER_READ)),
    EMPLOYER(Sets.newHashSet(EMPLOYER_READ, EMPLOYER_WRITE, DECADEV_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
