package org.example.employeesystem.Lab3.service;

import org.example.employeesystem.Lab3.Entity.Employee;
import org.example.employeesystem.Lab3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee createEmployee(Employee e) {
        return repo.save(e);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repo.findById(id);
    }

    public Optional<Employee> updateEmployee(Long id, Employee newData) {
        return repo.findById(id).map(existing -> {
            existing.setName(newData.getName());
            existing.setDepartment(newData.getDepartment());
            existing.setPosition(newData.getPosition());
            existing.setHireDate(newData.getHireDate());
            return repo.save(existing);
        });
    }

    public boolean deleteEmployee(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
