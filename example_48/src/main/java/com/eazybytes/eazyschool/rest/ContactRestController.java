package com.eazybytes.eazyschool.rest;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eazybytes.eazyschool.constant.EazySchoolConstants.CLOSE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping( path = "/api/contact", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE } )
@CrossOrigin( origins = "*" )
public class ContactRestController {
    final ContactRepository contactRepository;

    @GetMapping( "/getMessagesByStatus" )
    public List<Contact> getMessagesByStatus( @RequestParam String status ) {
        return contactRepository.findByStatus( status );
    }

    @GetMapping( "/getAllMsgsByStatus" )
    public List<Contact> getAllMsgsByStatus( @RequestBody Contact contact ) {
        if ( contact != null && contact.getStatus() != null ) {
            return contactRepository.findByStatus( contact.getStatus() );
        } else return List.of();
    }

    @PostMapping( "/saveMsg" )
    public ResponseEntity<Response> saveMsg( @RequestHeader( "invocationFrom" ) String invocationFrom,
                                             @Valid @RequestBody Contact contact ) {
        log.info( String.format( "Header invocationFrom = %s", invocationFrom ) );
        contactRepository.save( contact );
        Response response = new Response( "200", "Message saved successfully" );
        return ResponseEntity
                .status( CREATED )
                .header( "isMsgSaved", "true" )
                .body( response );
    }

    @DeleteMapping( "/deleteMsg" )
    public ResponseEntity<Response> deleteMsg( RequestEntity<Contact> requestEntity ) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach( ( key, value ) -> {
            log.info( String.format(
                    "Header '%s' = %s", key, value.stream().collect( Collectors.joining( "|" ) ) ) );
        } );
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById( contact.getContactId() );
        Response response = new Response();
        response.setStatusCode( "200" );
        response.setStatusMsg( "Message successfully deleted" );
        return ResponseEntity
                .status( OK )
                .body( response );
    }

    @PatchMapping( "/closeMsg" )
    public ResponseEntity<Response> closeMsg( @RequestBody Contact contactReq ) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById( contactReq.getContactId() );
        if ( contact.isPresent() ) {
            contact.get().setStatus( CLOSE );
            contactRepository.save( contact.get() );
        } else {
            response.setStatusCode( "400" );
            response.setStatusMsg( "Invalid Contact ID received" );
            return ResponseEntity
                    .status( BAD_REQUEST )
                    .body( response );
        }
        response.setStatusCode( "200" );
        response.setStatusMsg( "Message successfully closed" );
        return ResponseEntity
                .status( OK )
                .body( response );
    }
}
