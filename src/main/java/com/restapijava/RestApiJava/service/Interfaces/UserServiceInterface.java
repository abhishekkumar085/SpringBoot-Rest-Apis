package com.restapijava.RestApiJava.service.Interfaces;

import com.restapijava.RestApiJava.model.User;

import java.util.List;

public interface UserServiceInterface {
    
    User createUser(User user);

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(long id, User userDetails);

    void deleteUser(long id);

}