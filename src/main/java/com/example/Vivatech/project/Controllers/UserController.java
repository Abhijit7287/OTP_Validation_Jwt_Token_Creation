package com.example.Vivatech.project.Controllers;

import com.example.Vivatech.project.DTOs.AddUser;
import com.example.Vivatech.project.DTOs.JwtToken;
import com.example.Vivatech.project.DTOs.RequestLogin;
import com.example.Vivatech.project.DTOs.UserProfile;
import com.example.Vivatech.project.JwtHelper.JwtUtil;
import com.example.Vivatech.project.Services.CustomUserDetails;
import com.example.Vivatech.project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody AddUser addUser){

        userService.addUser(addUser);

        if(!userService.check(addUser.getEmailId())){
            return new ResponseEntity("Validate your emailId first",HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = customUserDetails.loadUserByUsername(addUser.getUserName());

        String token  = jwtUtil.generateToken(userDetails);

        JwtToken jwtToken = JwtToken.builder().token(token).build();

        return new ResponseEntity(jwtToken, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity userLogIn(@RequestBody RequestLogin requestLogin){

        UserDetails userDetails = customUserDetails.loadUserByUsername(requestLogin.getUserName());

        String token  = jwtUtil.generateToken(userDetails);

        JwtToken jwtToken = JwtToken.builder().token(token).build();

        return new ResponseEntity(jwtToken, HttpStatus.CREATED);

    }

    @GetMapping("/getUserProfile")
    public ResponseEntity getProfile(@RequestBody RequestLogin requestLogin){

        UserProfile userProfile = userService.getUser(requestLogin);

        return new ResponseEntity(userProfile,HttpStatus.OK);
    }
}
