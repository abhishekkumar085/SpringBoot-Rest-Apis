package com.restapijava.RestApiJava.service;

import com.restapijava.RestApiJava.model.Employee;
import com.restapijava.RestApiJava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        // Save the employee object to the database
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        // Retrieve employee by ID, throw an exception if not found
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Retrieve all employees from the database
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(long id, Employee employeeDetails) {
        // Retrieve the employee by ID
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        // Update fields
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setAge(employeeDetails.getAge());

        // Save updated employee object
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        // Check if the employee exists before deleting
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
