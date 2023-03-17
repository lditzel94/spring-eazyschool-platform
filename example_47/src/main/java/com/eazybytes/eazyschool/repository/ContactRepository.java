package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
    List<Contact> findByStatus( String status );

    //@Query( value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true )
    /*@Query( "SELECT c FROM Contact c WHERE c.status = :status" )
    Page<Contact> findByStatus( @Param( "status" ) String state, Pageable pageable );*/
    @Query( "SELECT c FROM Contact c WHERE c.status = :status" )
    Page<Contact> findByStatusWithQuery( String status, Pageable pageable );

    @Transactional
    @Modifying
    @Query( "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2" )
    int updateStatusById( String status, int id );

    Page<Contact> findOpenMsgs( String status, Pageable pageable );

    @Transactional
    @Modifying
    int updateMsgStatus( String status, int id );

    @Query( nativeQuery = true )
    Page<Contact> findOpenMsgsNative( String status, Pageable pageable );

    @Transactional
    @Modifying
    @Query( nativeQuery = true )
    int updateMsgStatusNative( String status, int id );
}
