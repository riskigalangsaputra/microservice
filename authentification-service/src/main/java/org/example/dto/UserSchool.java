package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserSchool {

    private String id;
    private String email;
    private String password;
    private String role;
}
