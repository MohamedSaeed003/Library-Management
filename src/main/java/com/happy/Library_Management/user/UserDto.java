package com.happy.Library_Management.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String fullName;
    private List<String> roles;
}
