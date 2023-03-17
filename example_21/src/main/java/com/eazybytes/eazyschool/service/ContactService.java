package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.controller.ContactController;
import com.eazybytes.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {

    // This boilerplate code may be skipped with @Slf4j annotation
    /*private static Logger log = LoggerFactory.getLogger( ContactService.class );*/

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
