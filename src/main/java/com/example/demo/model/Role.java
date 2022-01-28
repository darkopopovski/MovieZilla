package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

//    ROLE_CLIENT
    Client, ROLE_EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }


}
