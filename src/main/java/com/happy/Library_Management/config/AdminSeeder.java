package com.happy.Library_Management.config;

import com.happy.Library_Management.role.Role;
import com.happy.Library_Management.role.RoleRepository;
import com.happy.Library_Management.user.User;
import com.happy.Library_Management.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String email = "admin@library.com";

        if (userRepository.findByEmail(email).isEmpty()) {
            Role adminRole = roleRepository.findByRoleName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

            User admin = User.builder()
                    .firstName("library")
                    .lastName("admin")
                    .email(email)
                    .password(passwordEncoder.encode("passwordadmin")) // change to env var for security
                    .roles(Set.of(adminRole))
                    .enabled(true)
                    .build();

            userRepository.save(admin);
            System.out.println("✅ Default admin created: " + email);
        }
    }
}
