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

    /**
     * 根据手机号查找用户，如果手机号被注册过，则返回空值
     * @param user
     * @return
     */
    public User saveUser(User user){
        if(userRepository.findByPhone(user.getPhone())==null){
            user.setPassword(encoder.encode(user.getPassword()));
            return  userRepository.save(user);
        }else{
            return null;
        }
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }



}
