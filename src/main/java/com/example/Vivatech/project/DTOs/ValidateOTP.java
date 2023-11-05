package com.example.Vivatech.project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateOTP {

    private Integer otp;
    private String emailId;
}
