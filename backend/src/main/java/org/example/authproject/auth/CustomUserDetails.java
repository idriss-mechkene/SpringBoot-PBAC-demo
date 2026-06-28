package org.example.authproject.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.example.authproject.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        if (user.getPermission() != null) {
            authorities.addAll(user.getPermission().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .toList());
        }
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

}