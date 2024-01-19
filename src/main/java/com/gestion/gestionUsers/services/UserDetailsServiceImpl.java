/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.services;

import com.gestion.gestionUsers.model.Role;
import com.gestion.gestionUsers.model.User;
import com.gestion.gestionUsers.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ibrahim
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public UserDetailsService loadUserByUsername(String username) throws  UsernameNotFoundException{
        
        User user = this.userRepository.findByCodeUser(username);
        Set<String> authorities = new HashSet<>();
        
        if(user!= null){
            for(Role role : user.getRoles()){
                authorities.add(role.getName());
            }
        }
        List<GrantedAuthority> grantedAuthorities = authorities.stream().map(authority->new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        
        return (UserDetailsService) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        
    }
}
