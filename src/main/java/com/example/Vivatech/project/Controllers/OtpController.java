package com.example.Vivatech.project.Controllers;

import com.example.Vivatech.project.DTOs.RequestOTP;
import com.example.Vivatech.project.DTOs.ValidateOTP;
import com.example.Vivatech.project.Services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/generateOTP")
    public ResponseEntity generateOTP(@RequestBody RequestOTP requestOTP) throws MessagingException {

        try {
            String msg = otpService.sendOtp(requestOTP);
            return new ResponseEntity(msg,HttpStatus.OK);
        }catch(MessagingException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validateOTP")
    public ResponseEntity validateOTP(@RequestBody ValidateOTP validateOTP){

       String msg = otpService.validateOTP(validateOTP);

       return new ResponseEntity(msg,HttpStatus.OK);
    }
}
