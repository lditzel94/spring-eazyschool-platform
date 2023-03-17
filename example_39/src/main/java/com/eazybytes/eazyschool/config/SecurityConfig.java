package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Deprecated, refer to -> https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        //disable csrf configuration
        //ignore for contact form
        httpSecurity.csrf().ignoringAntMatchers( "/saveMsg" ).ignoringAntMatchers( "/public/**" ).and()
                    //Authorize requests
                    .authorizeRequests()
                    //Concat mvc matchers to specific paths
                    .mvcMatchers( "/dashboard" ).authenticated()
                    .mvcMatchers( "/displayMessage" ).hasRole( "ADMIN" ) //Allows path matcher to specified user role
                    .mvcMatchers( "/home" ).permitAll()
                    .mvcMatchers( "/holidays/**" ).permitAll()
                    .mvcMatchers( "/contact" ).permitAll()
                    .mvcMatchers( "/saveMsg" ).permitAll()
                    .mvcMatchers( "/courses" ).permitAll()
                    .mvcMatchers( "/about" ).permitAll()
                    .mvcMatchers( "/login" ).permitAll()
                    .mvcMatchers( "/public/**" ).permitAll()
                    // Enable custom login page display
                    .and().formLogin().loginPage( "/login" )
                    // login page resolver
                    .defaultSuccessUrl( "/dashboard" ).failureUrl( "/login?error=true" ).permitAll().and()
                    // logout setup
                    .logout().logoutSuccessUrl( "/login?logout=true" ).invalidateHttpSession( true ).and()
                    .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
