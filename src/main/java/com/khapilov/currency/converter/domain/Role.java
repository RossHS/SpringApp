package com.khapilov.currency.converter.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Ross Khapilov
 * @version 1.0 01.08.2020
 */
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
