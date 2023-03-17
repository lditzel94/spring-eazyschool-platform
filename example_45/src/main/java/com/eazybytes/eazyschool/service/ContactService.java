package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constant.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Page<Contact> findMsgsWithOpenStatus( int pageNum, String sortField, String sortDir ) {
        int pageSize = 5;
        //Page number in JPA starts at index zero
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize,
                                            sortDir.equals( "asc" ) ? Sort.by( sortField ).ascending() : Sort.by( sortField ).descending() );
        return contactRepository.findByStatus( EazySchoolConstants.OPEN, pageable );
    }

    public boolean updateMsgStatus( int contactId ) {
        int rows = contactRepository.updateMsgStatus( CLOSE, contactId );
        if ( rows > 0 ) return true;
        return false;
    }
}
