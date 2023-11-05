package com.example.Vivatech.project.DTOs;

import com.example.Vivatech.project.Enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfile {

    private String firstName;

    private String lastName;

    private Gender gender;

}
