package com.example.Vivatech.project.Transformers;


import com.example.Vivatech.project.DTOs.AddUser;
import com.example.Vivatech.project.DTOs.UserProfile;
import com.example.Vivatech.project.Models.User;

public class UserTransformer {

    public static User DtoToUser(AddUser addUser) {

        User user = User.builder().emailId(addUser.getEmailId())
                .gender(addUser.getGender())
                .password(addUser.getPassword())
                .userName(addUser.getUserName())
                .build();

        return user;
    }

    public static UserProfile userToUserProfile(User user) {

        UserProfile userProfile = UserProfile.builder()
                .firstName(user.getUserName())
                .lastName(user.getPassword())
                .gender(user.getGender())
                .build();

        return userProfile;
    }

}