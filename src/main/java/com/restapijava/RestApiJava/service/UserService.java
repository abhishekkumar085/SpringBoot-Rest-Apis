package com.restapijava.RestApiJava.service;

import com.restapijava.RestApiJava.model.User;

import com.restapijava.RestApiJava.repository.UserRepository;

import com.restapijava.RestApiJava.service.Interfaces.UserServiceInterface;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        try{
               // check if email already exists
            if(userRepository.findByEmail(user.getEmail()).isPresent()){
                throw new RuntimeException("Email already exists");
            }

            // Ensure the password is plain text before encoding
            if (!user.getPassword().startsWith("$2a$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
         

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch(RuntimeException e){
              // Preserve specific exception messages
            if ("Email already exists".equals(e.getMessage())) {
                throw e; // Re-throw the original exception
            }

            throw new RuntimeException("Error creating user",e);
        }
      
        
    }

    @Override
    public User getUserById(long id) {
    return userRepository.findById(id).orElse(null);
       
        
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(long id, User userDetails) {
        try{
            User user = userRepository.findById(id).orElse(null);
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            return userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void deleteUser(long id) {
        try{
            User user = userRepository.findById(id).orElse(null);
            userRepository.delete(user);

        }catch(Exception e){
            throw new RuntimeException("Something went wrong");
        }
    }

}