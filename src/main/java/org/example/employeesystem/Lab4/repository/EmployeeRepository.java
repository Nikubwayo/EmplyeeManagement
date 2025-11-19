package org.example.employeesystem.Lab4.repository;

import org.example.employeesystem.Lab4.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
