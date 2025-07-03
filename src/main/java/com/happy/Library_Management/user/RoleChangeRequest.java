package com.happy.Library_Management.user;

import lombok.Data;
import java.util.Set;

@Data
public class RoleChangeRequest {
    private Set<String> roles;
}
