package com.restapijava.RestApiJava.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Employee{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private long id;
    @Column(name="first_name",nullable=false)
    private String firstName;

    @Column(name="last_name",nullable=false)
    private String lastName;

    @Column(name="email",nullable=false)
    private String email;

    @Column(name="age",nullable=false)
    private int age;


    public Employee() {
    }

    // parameterized constructor

    public Employee(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id=id;
    
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getEmail(){
        return email;

    }

    public void setEmail(String email){
        this.email=email;
    }
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age=age;   
    }
    
  @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}


