package com.taskflow.service3.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDetailsDTO {

    Integer userId;
    String firstName;
    String lastName;
    String email;
    String role;
    public UserDetailsDTO(
            Integer userId,
            String firstName,
            String lastName,
            String email,
            String role
    ){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
