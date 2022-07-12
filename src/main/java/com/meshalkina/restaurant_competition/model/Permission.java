package com.meshalkina.restaurant_competition.model;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
