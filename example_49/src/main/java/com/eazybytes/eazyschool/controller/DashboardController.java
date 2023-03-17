package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

    final PersonRepository personRepository;

    @Value( "${eazyschool.pageSize}" )
    private int defaultPageSize;

    @Value( "${eazyschool.contact.successMsg}" )
    private String message;

    public DashboardController( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    @RequestMapping( "/dashboard" )
    public String displayDashboard( Model model, Authentication authentication,
                                    HttpSession session ) { // Spring security injects Authentication object initially built in SecurityConfig file
        Person person = personRepository.readByEmail( authentication.getName() ); //Using person email as name
        model.addAttribute( "username", authentication.getName() );
        model.addAttribute( "roles", authentication.getAuthorities().toString() );
        if ( null != person.getEazyClass() && null != person.getEazyClass().getName() ) {
            model.addAttribute( "enrolledClass", person.getEazyClass().getName() );
        }
        session.setAttribute( "loggedInPerson", person );
        logMessages();
        return "dashboard.html";
    }

    private void logMessages() {
        log.error( "Error message from the Dashboard page" );
        log.warn( "Warning message from the Dashboard page" );
        log.info( "Info message from the Dashboard page" );
        log.debug( "Debug message from the Dashboard page" );
        log.trace( "Trace message from the Dashboard page" );

        log.error( "defaultPageSize value with @Value annotation is: " + defaultPageSize );
        log.error( "successMsg value with @Value annotation is: " + message );
    }

}