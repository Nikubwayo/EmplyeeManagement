package org.example.employeesystem.Lab4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Position cannot be empty")
    private String position;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;
}
