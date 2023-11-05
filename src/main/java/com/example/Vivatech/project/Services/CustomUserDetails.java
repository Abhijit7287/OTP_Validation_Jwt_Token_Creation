package com.example.Vivatech.project.Services;

import com.example.Vivatech.project.Models.User;
import com.example.Vivatech.project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        UserDetails userDetails;
        try {
            // Create a UserDetails object based on the User entity
             userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    Collections.emptyList()
            );
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("user name not found");
        }catch(BadCredentialsException e){
           throw new BadCredentialsException("Bad Credentials");
        }

        return userDetails;
    }
}
