package org.example.authproject.config;

import java.util.HashSet;
import java.util.Set;

import org.example.authproject.entity.User;
import org.example.authproject.enums.EPermission;
import org.example.authproject.enums.ERole;
import org.example.authproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInit {
    
    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                // Create an admin user if it doesn't exist
                User adminUser = new User();
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword(passwordEncoder.encode("adminpassword"));
                adminUser.setRole(ERole.ADMIN);
                userRepository.save(adminUser);
                System.out.println("Admin user created: " + adminUser.getEmail());
                // Remove the following block if you don't want to add Users
                User normalUser = new User();
                Set<EPermission> permissions = new HashSet<>();
                normalUser.setEmail("user@example.com");
                normalUser.setPassword(passwordEncoder.encode("userpassword"));
                normalUser.setRole(ERole.USER);
                permissions.add(EPermission.READ);
                normalUser.setPermission(permissions);
                userRepository.save(normalUser);
                
                System.out.println("Normal user created: " + normalUser.getEmail());
            }
        };
    }
}
