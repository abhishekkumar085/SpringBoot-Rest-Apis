package com.restapijava.RestApiJava.controller;

import java.util.List;

import com.restapijava.RestApiJava.util.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.restapijava.RestApiJava.service.UserService;

import org.springframework.web.bind.annotation.*;

import com.restapijava.RestApiJava.model.User;


@RestController
@RequestMapping("/api/v1/users")

public class UserController{

    @Autowired

    private UserService userService;

    @PostMapping
    public ApiResponse<User>createUser(@RequestBody(required=false) User user){
        // if request body is empty, return bad request
        if(user == null){
            return new ApiResponse<>(false, "Request body is empty", null);
        }


        try{
            return new ApiResponse<>(true,"User created successfully!!",userService.createUser(user));
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
      
    }

    @GetMapping
    public ApiResponse<List<User>>getAllUsers(){

        try{
            return new ApiResponse<>(true, "Users retrieved successfully.", userService.getAllUsers());
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
        

    }
    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable("id") long id) {
        try{
            return new ApiResponse<>(true, "User retrieved successfully.", userService.getUserById(id));
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
       
    }

    @GetMapping("/email/{email}")
    public ApiResponse<User> getUserByEmail(@PathVariable("email") String email) {
        try{
            return new ApiResponse<>(true, "User retrieved successfully.", userService.getUserByEmail(email));
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
        
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable("id") long id, @RequestBody User userDetails) {
        try{
            return new ApiResponse<>(true, "User updated successfully.", userService.updateUser(id, userDetails));
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
       
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable("id") long id) {
        try{
            userService.deleteUser(id);
            return new ApiResponse<>(true, "User deleted successfully.", null);
        }catch(Exception e){
            return new ApiResponse<>(false, e.getMessage(), null);
        }
        
    }

}