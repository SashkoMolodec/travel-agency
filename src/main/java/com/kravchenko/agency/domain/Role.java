package com.kravchenko.agency.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }

}
