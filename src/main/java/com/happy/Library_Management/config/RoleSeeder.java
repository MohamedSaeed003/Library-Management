package com.happy.Library_Management.config;

import com.happy.Library_Management.role.Role;
import com.happy.Library_Management.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> roleNames = List.of("ADMIN", "LIBRARIAN", "STAFF", "MEMBER");

        for (String roleName : roleNames) {
            roleRepository.findByRoleName(roleName)
                    .orElseGet(() -> roleRepository.save(Role.builder().roleName(roleName).build()));
        }
    }
}

