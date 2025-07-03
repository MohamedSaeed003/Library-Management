package com.happy.Library_Management.admin;

import com.happy.Library_Management.user.RoleChangeRequest;
import com.happy.Library_Management.user.UserDto;
import com.happy.Library_Management.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/users/{userId}/roles")
    public ResponseEntity<?> updateUserRoles(
            @PathVariable Integer userId,
            @RequestBody RoleChangeRequest request
    ) {
        userService.updateUserRoles(userId, request.getRoles());
        return ResponseEntity.ok("Roles updated successfully");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
