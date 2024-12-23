package com.restapijava.RestApiJava.controller;

import com.restapijava.RestApiJava.model.Employee;

import com.restapijava.RestApiJava.service.EmployeeServiceImp;

import com.restapijava.RestApiJava.util.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/employees")

public class EmployeeController{

    @Autowired
    
    private EmployeeServiceImp employeeServiceImp;

    @PostMapping

    public ApiResponse<Employee>createEmployee(@RequestBody Employee employee){
        // return ResponseEntity.status(HttpStatus.CREATED).body(employeeServiceImp.createEmployee(employee));
       return new ApiResponse<>(true,"Employee created successfully!!",employeeServiceImp.createEmployee(employee));
    }

    @GetMapping

    public ApiResponse<List<Employee>>getAllEmployees(){

        return new ApiResponse<>(true, "Employees retrieved successfully.", employeeServiceImp.getAllEmployees());

    }

    @GetMapping("/{id}")  

    public ApiResponse<Employee>getEmployeeById(@PathVariable("id") long id){

        return new ApiResponse<>(true, "Employee retrieved successfully.", employeeServiceImp.getEmployeeById(id));

    }

    // Update Employee
    @PutMapping("/{id}")

  public ApiResponse<Employee>updateEmployee(@PathVariable("id") long id, @RequestBody Employee employeeDetails){

    return new ApiResponse<>(true, "Employee updated successfully.", employeeServiceImp.updateEmployee(id, employeeDetails));

    }

    @DeleteMapping("/{id}")

  public ApiResponse<Void>deleteEmployee(@PathVariable("id") long id){

    employeeServiceImp.deleteEmployee(id);

    return new ApiResponse<>(true, "Employee deleted successfully.", null);

    }
}