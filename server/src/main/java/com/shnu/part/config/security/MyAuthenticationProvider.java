package com.shnu.part.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * spring security 会将表单内容封装成一个Authentication实现类。
 * 需要自定义认证逻辑时候，可以通过实现AuthenticationProvider来完成。然后注册到AuthenticationManagerBuilder即可
 * UserDetailsService 只负责获取用户数据，通过loadByUserName()方法返回一个UserDetail 实例，而不负责认证逻辑
 *
 * 1.用户使用用户名和密码进行登录。
 * 2.Spring Security将获取到的用户名和密码封装成一个Authentication接口的实现类，比如常用的UsernamePasswordAuthenticationToken。
 * 3将上述产生的Authentication对象传递给AuthenticationManager的实现类ProviderManager进行认证。
 * 3.ProviderManager依次调用各个AuthenticationProvider进行认证，认证成功后返回一个封装了用户权限等信息的Authentication对象。
 * 4.将AuthenticationManager返回的Authentication对象赋予给当前的SecurityContext。
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    private UserDetailsService userDetailsService;
    private PasswordEncoder encoder;
    private UserDetails userDetails;



    public MyAuthenticationProvider userDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
        return this;
    }

    public MyAuthenticationProvider passwordEncoder(PasswordEncoder encoder){
        this.encoder = encoder;
        return this;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        userDetails = userDetailsService.loadUserByUsername(name);

        if(userDetails!=null&&encoder.matches(password,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        }else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
