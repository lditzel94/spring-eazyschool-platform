package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

    final PersonRepository personRepository;

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
        return "dashboard.html";
    }

}