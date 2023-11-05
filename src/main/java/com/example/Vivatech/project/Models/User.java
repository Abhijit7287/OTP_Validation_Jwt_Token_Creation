package com.example.Vivatech.project.Models;

import com.example.Vivatech.project.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String userName;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String emailId;

}
