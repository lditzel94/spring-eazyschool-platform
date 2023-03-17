package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RequestMapping( "public" )
@Controller
public class PublicController {

    final PersonService personService;

    public PublicController( PersonService personService ) {
        this.personService = personService;
    }

    @RequestMapping( value = "/register", method = { GET } )
    public String displayRegisterPage( Model model ) {
        model.addAttribute( "person", new Person() );
        return "register.html";
    }

    @RequestMapping( value = "/createUser", method = { POST } )
    public String createUser( @Valid @ModelAttribute( "person" ) Person person, Errors errors ) {

        if ( errors.hasErrors() ) {
            return "register.html";
        }
        boolean isSaved = personService.createNewPerson( person );
        if ( isSaved ) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }
}
