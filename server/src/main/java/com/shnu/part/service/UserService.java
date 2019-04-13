package com.shnu.part.service;

import com.shnu.part.domain.User;
import com.shnu.part.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User saveUser(User user){
        if(userRepository.findUserByEmail(user.getEmail())==null){
            user.setPassword(encoder.encode(user.getPassword()));
            return  userRepository.save(user);
        }else{
            return null;
        }
    }


}
