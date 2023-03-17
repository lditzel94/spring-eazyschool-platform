package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.eazybytes.eazyschool.constant.EazySchoolConstants.CLOSE;
import static com.eazybytes.eazyschool.constant.EazySchoolConstants.OPEN;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService( ContactRepository contactRepository ) {
        this.contactRepository = contactRepository;
        System.out.println( "Contact Service Bean initialized" );
    }

    /**
     * Save Contact Details into DB
     *
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails( Contact contact ) {
        boolean isSaved = false;
        contact.setStatus( OPEN );

        Contact savedContact = contactRepository.save( contact );
        if ( null != savedContact && savedContact.getContactId() > 0 ) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findByStatus( OPEN );
        return contactMsgs;
    }

    public boolean updateMsgStatus( int contactId ) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById( contactId );
        contact.ifPresent( contact1 -> {
            contact1.setStatus( CLOSE );
        } );
        Contact updatedContact = contactRepository.save( contact.get() );
        if ( null != updatedContact && updatedContact.getUpdatedBy() != null ) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
