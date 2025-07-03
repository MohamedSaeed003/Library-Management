package com.happy.Library_Management;

import com.happy.Library_Management.role.Role;
import com.happy.Library_Management.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class LibraryManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			String[] roles = {"ADMIN", "LIBRARIAN", "STAFF", "MEMBER"};
			for (String roleName : roles) {
				if (roleRepository.findByRoleName(roleName).isEmpty()) {
					roleRepository.save(Role.builder().roleName(roleName).build());
					System.out.println("Created role: " + roleName);
				}
			}
			System.out.println("✔ Default roles checked/created successfully.");
		};
	}
}
