package dev.deerops.contentmanagementapi.user.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    OWNER,ADMIN,MODERATOR,STAFF;

    @Override
    public String getAuthority() {
        return name();
    }
}
