package com.decagonhq.hireday.security;

public enum UserPermission {
    DECADEV_READ("decadev:read"),
    DECADEV_WRITE("decadev:write"),
    EMPLOYER_READ("employer:read"),
    EMPLOYER_WRITE("employer:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
