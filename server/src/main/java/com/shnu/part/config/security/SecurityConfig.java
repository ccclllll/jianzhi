package com.shnu.part.config.security;


import com.shnu.part.config.jwt.JWTAuthenticationFilter;
import com.shnu.part.config.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailService userDetailService;

    /**
     * 基本配置
     *
     * @param http
     * @throws Exception
     */
/*
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests().antMatchers("/login")
                .permitAll();
        http.csrf().disable();
    }
*/




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
        //解决如下异常信息：
        //Refused to display in a frame because it set ‘X-Frame-Options’ to ‘DENY’
        http.headers().frameOptions().disable();

    }


/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/api/**").permitAll();
    }
*/

/*
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.providerDetails());
    }


    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(this.clientRegistrationRepository());
    }
*/



/*    public ClientRegistration providerDetails() {

        return ClientRegistration.withRegistrationId("merry")
                .clientId("merryyou3")
                .clientSecret("merryyousecrect3")
                .authorizationUri("http://localhost:8082/uaa/oauth/authorize")
                .tokenUri("http://localhost:8082/uaa/oauth//oauth/token")
                .jwkSetUri("http://localhost:8082/uaa/oauth//oauth/check-token")
                .authorizationGrantType(new AuthorizationGrantType("authorization_code"))
                .redirectUriTemplate("http://localhost:8080/user")
                .scope(new String[]{"all"})
                .clientName("merry")
                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)
                .build();
    }*/

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

/*
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new MyAuthenticationProvider()
                .passwordEncoder(encoder())
                .userDetailsService(userDetailService);
    }
*/


    /**
     * 这里使用 框架提供的 DaoAuthenticationProvider 进行验证
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); //注册AuthenticationProvider
    }

}
