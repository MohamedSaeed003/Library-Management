package com.happy.Library_Management.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @Email(message = "Email is not formatted ")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 10, message = "Password must be 10 or more")
    private String password;
}
