package com.shnu.part.web.api;

import com.shnu.part.domain.User;
import com.shnu.part.service.UserService;
import com.shnu.part.web.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
public class UserResource {
    private final Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 调用spring security 对用户名和密码进行验证 通过则返回token

     * @return
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JwtToken> authenticate(@RequestBody LoginVM loginVM ) {
        try {
            // 内部登录请求
            UsernamePasswordAuthenticationToken authRequest
                    = new UsernamePasswordAuthenticationToken(loginVM.getEmail(), loginVM.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            // 验证
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authenticationManager.authenticate(authRequest));

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new JwtToken(null), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new JwtToken(JwtUtil.buildJWTToken(loginVM.getEmail())), HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }

    public class JwtToken {
        String token;

        public JwtToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

   class LoginVM{
       private  String email;
       private String password;

       public String getEmail() {
           return email;
       }

       public void setEmail(String email) {
           this.email = email;
       }

       public String getPassword() {
           return password;
       }

       public void setPassword(String password) {
           this.password = password;
       }
   }
}
