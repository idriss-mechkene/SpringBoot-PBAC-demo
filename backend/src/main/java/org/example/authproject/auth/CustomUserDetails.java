package org.example.authproject.auth;

import java.util.Collection;
import java.util.List;

import org.example.authproject.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    
    @Override
    public String getUsername() {
        return user.getEmail();
    }
    


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(() -> user.getRole());
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

}