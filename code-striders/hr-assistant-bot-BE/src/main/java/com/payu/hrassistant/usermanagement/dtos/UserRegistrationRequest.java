package com.payu.hrassistant.usermanagement.dtos;

import com.payu.hrassistant.usermanagement.enums.Role;

public class UserRegistrationRequest {
 
    private String username;
    private String password;
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
 
    // Getters and Setters
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public Role getRole() {
        return role;
    }
 
    public void setRole(Role role) {
        this.role = role;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
this.email = email;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}