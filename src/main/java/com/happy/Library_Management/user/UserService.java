package com.happy.Library_Management.user;

import com.happy.Library_Management.role.Role;
import com.happy.Library_Management.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserDto getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(this::mapToDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void updateUserRoles(Integer userId, Set<String> roleNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Role> roles = roleRepository.findAll().stream()
                .filter(role -> roleNames.contains(role.getRoleName()))
                .toList();

        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.fullName())
                .roles(user.getRoles().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toList()))
                .build();
    }
}
