/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.repository;

import com.gestion.gestionUsers.model.Role;
import com.gestion.gestionUsers.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ibrahim
 */
@Repository
public interface UserRepository extends CrudRepository<User , Long>{    

    public boolean existsByRolesContains(Role role);
    
    @Query("SELECT username FROM users where username=:codeUser OR " + "email=:codeUser")

    public User findByCodeUser(@Param("codeUser")String codeUser);
    
}
