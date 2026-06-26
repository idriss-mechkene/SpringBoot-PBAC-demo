package org.example.authproject.config;

import org.example.authproject.entity.User;
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
                adminUser.setRole("ROLE_ADMIN");
                userRepository.save(adminUser);
                System.out.println("Admin user created: " + adminUser.getEmail());
            }
        };
    }
}
