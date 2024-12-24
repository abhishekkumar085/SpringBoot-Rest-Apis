package com.restapijava.RestApiJava.service.Interfaces;

import com.restapijava.RestApiJava.model.Employee;

import java.util.List;



public interface EmployeeServiceInterface {

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(long id, Employee employeeDetails);

    void deleteEmployee(long id);

}
