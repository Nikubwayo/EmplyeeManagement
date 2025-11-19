package org.example.employeesystem.Lab4.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.example.employeesystem.Lab4.enums.Role;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the user", example = "1")
    private Long id;

    @Column(unique = true)
    @Schema(description = "Username of the user", example = "ruth123")
    private String username;

    @Column(unique = true)
    @Schema(description = "Email of the user", example = "ruth@example.com")
    private String email;

    @Schema(description = "Encoded password of the user", example = "$2a$10$...")
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Role assigned to the user", example = "ROLE_ADMIN")
    private Role role;
}
