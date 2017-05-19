package model.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RoleException extends UsernameNotFoundException {
    public RoleException(String message) {
        super(message);
    }
}