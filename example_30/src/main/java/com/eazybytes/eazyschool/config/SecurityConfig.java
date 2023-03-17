package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Deprecated, refer to -> https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {
        //disable csrf configuration
        httpSecurity.csrf().disable()
                //Authorize requests
                .authorizeRequests()
                //Concat mvc matchers to specific paths
                .mvcMatchers( "/dashboard" ).authenticated()
                .mvcMatchers( "/home" ).permitAll()
                .mvcMatchers( "/holidays/**" ).permitAll()
                .mvcMatchers( "/contact" ).permitAll()
                .mvcMatchers( "/saveMsg" ).permitAll()
                .mvcMatchers( "/courses" ).permitAll()
                .mvcMatchers( "/about" ).permitAll()
                .mvcMatchers( "/login" ).permitAll()
                // Enable custom login page display
                .and().formLogin().loginPage( "/login" )
                // login page resolver
                .defaultSuccessUrl( "/dashboard" ).failureUrl( "/login?error=true" ).permitAll()
                // logout setup
                .and().logout().logoutSuccessUrl( "/login?logout=true" ).invalidateHttpSession( true )
                .and().httpBasic();

    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser( "user" ).password( "12345" ).roles( "USER" )
                .and()
                .withUser( "admin" ).password( "54321" ).roles( "USER", "ADMIN" )
                .and().passwordEncoder( NoOpPasswordEncoder.getInstance() );
    }
}
