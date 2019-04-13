package com.shnu.part.config.security;

import com.shnu.part.domain.User;
import com.shnu.part.repositiry.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义UserDetailsService
 * UsernamePasswordAuthenticationToken是通过输入的username和password生成的
 * 通过email加载userDetails  返回 org.springframework.security.core.userdetails.User
 * provider 通过匹配 org.springframework.security.core.userdetails.User的password和UsernamePasswordAuthenticationToken的password来进行验证
 */
@Service
public class SecurityUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(SecurityUserDetailService.class);
    @Autowired
    private UserRepository userRepository;

    private User user;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户:"+username+"尝试登陆");

        User user = userRepository.findUserByEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("用户"+username+" not exist!");
        }

        Set<GrantedAuthority> authSet = new HashSet<>();
        authSet.add(new SimpleGrantedAuthority("USER"));

        logger.info(user.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getState()==1?true:false,
                true,
                true,
                true,
                authSet);
    }
}
