package com.example.Vivatech.project.DTOs;

import com.example.Vivatech.project.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUser {

    private String userName;

    private String password;

    private Gender gender;

    private String emailId;
}
