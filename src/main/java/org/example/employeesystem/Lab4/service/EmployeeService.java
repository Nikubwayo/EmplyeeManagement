package org.example.employeesystem.Lab4.service;

import org.example.employeesystem.Lab4.Entity.Employee;
import org.example.employeesystem.Lab4.dto.EmployeeRequest;
import org.example.employeesystem.Lab4.Exception.ResourceNotFoundException;
import org.example.employeesystem.Lab4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee createEmployee(EmployeeRequest req) {
        Employee emp = new Employee();
        emp.setName(req.getName());
        emp.setPosition(req.getPosition());
        emp.setDepartment(req.getDepartment());
        emp.setHireDate(req.getHireDate());
        return repo.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }

    public Employee updateEmployee(Long id, EmployeeRequest req) {
        Employee emp = getById(id);
        emp.setName(req.getName());
        emp.setPosition(req.getPosition());
        emp.setDepartment(req.getDepartment());
        emp.setHireDate(req.getHireDate());
        return repo.save(emp);
    }

    public void deleteEmployee(Long id) {
        Employee emp = getById(id);
        repo.delete(emp);
    }
}
