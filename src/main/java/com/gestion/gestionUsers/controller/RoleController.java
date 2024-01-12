/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import com.gestion.gestionUsers.model.Role;
import com.gestion.gestionUsers.repository.RoleRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ibrahim
 */
@Controller
public class RoleController {
    @Autowired
    
    private RoleRepository roleRepository;
    
    @GetMapping("/listroles")
    public String ShowListRole(Model model){
        model.addAttribute("roles", roleRepository.findAll());
        return "layouts/roles";
    
    }
    
    @GetMapping("/createRole")
    public String FormCreateRole(Model model){
        model.addAttribute("role", new Role());
        return "layouts/addrole";
    }


    @PostMapping("/createRole")
    public String addUser(@Valid Role role, BindingResult result, Model model){
        if (result.hasErrors()){
            return "layouts/addrole";
        }
        
        roleRepository.save(role);
        return "redirect:/listroles";
    }

    @GetMapping("/deleterole/{id}")
    public String deleteRole(@PathVariable("id") long id, Model model) {
    Role role = roleRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    roleRepository.delete(role);
    return "redirect:/listroles";
}

}
