package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@Slf4j
@Getter
@Setter
/*@RequestScope*/ // Creates one Bean for every HTTP request the user makes
/*@SessionScope*/ // Creates only one Bean for the duration of the HTTP session
@ApplicationScope // Creates only one Bean at ServletContext level
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println( "Contact Service Bean initialized" );
    }

    /**
     * Save Contact Details into DB
     *
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails( Contact contact ) {
        boolean isSaved = true;
        //TODO - Need to persist the data into the DB table
        log.info( contact.toString() );
        return isSaved;
    }
}
