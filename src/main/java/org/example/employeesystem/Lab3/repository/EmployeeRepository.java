package org.example.employeesystem.Lab3.repository;

import org.example.employeesystem.Lab3.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
