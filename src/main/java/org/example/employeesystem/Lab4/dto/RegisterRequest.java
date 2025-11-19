package org.example.employeesystem.Lab4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    @Schema(description = "Unique username of the user", example = "ruth123", required = true)
    private String username;

    @NotBlank
    @Email
    @Schema(description = "User email address", example = "ruth@example.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "User password", example = "StrongPassword123!", required = true)
    private String password;

    @Schema(description = "Role of the user", example = "ADMIN or USER", defaultValue = "USER")
    private String role;
}
