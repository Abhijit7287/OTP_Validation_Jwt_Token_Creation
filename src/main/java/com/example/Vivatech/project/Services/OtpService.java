package com.example.Vivatech.project.Services;

import com.example.Vivatech.project.DTOs.RequestOTP;
import com.example.Vivatech.project.DTOs.ValidateOTP;
import com.example.Vivatech.project.Models.ValidatedEmail;
import com.example.Vivatech.project.Repositories.ValidateEmailRepo;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

     @Autowired
     private EmailService emailService;

     @Autowired
     private ValidateEmailRepo validateEmailRepo;


     private final Integer expTime = 4;

     private LoadingCache<String,Integer> cacheContainer;

     public OtpService(){
         cacheContainer = CacheBuilder.newBuilder().expireAfterWrite(expTime, TimeUnit.MINUTES)
                          .build(new CacheLoader<String, Integer>() {
                              @Override
                              public Integer load(String key){
                                  return 0;
                              }
                          });
     }

     public String sendOtp(RequestOTP requestOTP) throws MessagingException {

         Integer otp = generateOtp(requestOTP.getEmailId());

         return emailService.sendEmail(requestOTP.getEmailId(),otp);
     }

     public Integer generateOtp(String emailId){
         Random random = new Random();

         Integer otp = 100000+random.nextInt(800000);

         cacheContainer.put(emailId,otp);

         return otp;
     }

     public String validateOTP(ValidateOTP validateOTP){

         try{
             Integer check = cacheContainer.get(validateOTP.getEmailId());
             if(check.equals(validateOTP.getOtp())){
                 cacheContainer.invalidate(validateOTP.getEmailId());
                 ValidatedEmail validatedEmail = ValidatedEmail.builder().emailId(validateOTP.getEmailId()).build();
                 validateEmailRepo.save(validatedEmail);
                 return "We're pleased to confirm that the OTP you provided" +
                         " is correct and has been successfully validated.";
             }else{
                 return "We regret to inform you that the One-Time Password (OTP) you entered is incorrect." +
                         " Please ensure that you have entered the correct OTP";
             }

         }catch (ExecutionException e) {
           return e.getMessage();
         }
     }






}
