package com.SpringSecurity.MinimumToWork.repository.model;

import java.util.List;

public enum Role {
    ADMIN(List.of(
            Permissions.CREATE_USER,
            Permissions.EDIT_USER,
            Permissions.DISABLE_USER,
            Permissions.ENABLE_USER,
            Permissions.FIND_USERS,
            Permissions.FIND_ONE_USER,
            Permissions.VIEW_PROFILE
    )),
    USER(List.of(
            Permissions.CREATE_USER,
            Permissions.EDIT_USER,
            Permissions.DISABLE_USER,
            Permissions.ENABLE_USER,
            Permissions.FIND_USERS,
            Permissions.FIND_ONE_USER,
            Permissions.VIEW_PROFILE
    ));

    private final List<Permissions> permissions;


    Role(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }
}
