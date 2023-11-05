package com.example.Vivatech.project.Services;

import com.example.Vivatech.project.DTOs.AddUser;
import com.example.Vivatech.project.DTOs.RequestLogin;
import com.example.Vivatech.project.DTOs.UserProfile;
import com.example.Vivatech.project.Models.User;
import com.example.Vivatech.project.Models.ValidatedEmail;
import com.example.Vivatech.project.Repositories.UserRepository;
import com.example.Vivatech.project.Repositories.ValidateEmailRepo;
import com.example.Vivatech.project.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private ValidateEmailRepo validateEmailRepo;


    public void addUser(AddUser addUser) {

        User user = UserTransformer.DtoToUser(addUser);

        userRepository.save(user);

    }

    public boolean check(String emailId){
        ValidatedEmail validatedEmail = validateEmailRepo.findByEmail(emailId);

        if(validatedEmail!=null){
            return true;
        }else{
            return false;
        }
    }

    public UserProfile getUser(RequestLogin requestLogin){

        User user = userRepository.findByUsername(requestLogin.getUserName());

        UserProfile userProfile = UserTransformer.userToUserProfile(user);

        return userProfile;
    }

}