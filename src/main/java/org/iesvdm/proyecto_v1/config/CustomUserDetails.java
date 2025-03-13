/*package org.iesvdm.proyecto_v1.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

    private String email;

    public CustomUserDetails(String username, String password, String email, String... roles) {
        super(username, password, AuthorityUtils.createAuthorityList(roles));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}*/