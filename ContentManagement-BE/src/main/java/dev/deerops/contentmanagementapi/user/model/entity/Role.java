package dev.deerops.contentmanagementapi.user.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    OWNER,ADMIN,OTHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
