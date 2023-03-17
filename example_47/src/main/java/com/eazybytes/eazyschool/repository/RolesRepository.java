package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource( exported = false )
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName( String roleName );
}
