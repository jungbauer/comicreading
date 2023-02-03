package com.comicreading.security;

import com.comicreading.validation.PasswordMatches;
import com.comicreading.validation.ValidEmail;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
    
    @NotNull(message = "First name cannot be null.")
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;
    
    @NotNull(message = "Last name cannot be null.")
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;
    
    @NotNull(message = "Password cannot be null.")
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    private String matchingPassword;
    
    @ValidEmail
    @NotNull(message = "Email cannot be null.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

    
}
