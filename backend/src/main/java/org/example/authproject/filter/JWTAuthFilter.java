package org.example.authproject.filter;

import java.io.IOException;

import org.example.authproject.service.CustomUserDetailsService;
import org.example.authproject.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String authHeader = request.getHeader("Authorization");
        String email = null;
        String token = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtUtil.extractEmail(token);
        }
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if(jwtUtil.validateToken(token,userDetails, email)) {
                // Set the authentication in the context
                SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken (
                        userDetails, null, userDetails.getAuthorities()
                    )
                );
            }

        }
        filterChain.doFilter(request, response);

    }
}


