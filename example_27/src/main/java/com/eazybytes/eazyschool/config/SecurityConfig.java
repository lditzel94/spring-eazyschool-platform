package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Deprecated, refer to -> https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    
    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        // Permit all requests
        httpSecurity.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

        // Deny all requests
        /*httpSecurity.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();*/
    }
}
