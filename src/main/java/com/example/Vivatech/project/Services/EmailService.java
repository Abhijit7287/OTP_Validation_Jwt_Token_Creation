package com.example.Vivatech.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(String emailId,Integer otp) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        String body = "You have requested a one-time password (OTP) to sign up. Please use the this "
                + otp + " OTP to complete your action:";

      try {
          MimeMessageHelper helper = new MimeMessageHelper(message, true);
          helper.setFrom("cosmosrider001@gmail.com");
          helper.setTo(emailId);

          helper.setSubject("Hello , you are one step closer to your account");
          helper.setText(body);

          FileSystemResource resource = new FileSystemResource(new File("D:\\projectS\\welcome.jpg"));

          helper.addAttachment("vivatech", resource.getFile());

          javaMailSender.send(message);
          return "OTP has been send successfully";

      }catch(MessagingException e){
         return e.getMessage();
      }

    }
}
