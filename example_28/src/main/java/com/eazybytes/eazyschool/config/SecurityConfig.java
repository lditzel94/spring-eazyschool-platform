package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Deprecated, refer to -> https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .mvcMatchers( "/home" ).permitAll()
                .mvcMatchers( "/holidays/**" ).permitAll()
                .mvcMatchers( "/contact" ).permitAll()
                .mvcMatchers( "/saveMsg" ).permitAll()
                .mvcMatchers( "/courses" ).permitAll()
                .mvcMatchers( "/about" ).permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
