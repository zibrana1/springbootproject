/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import com.gestion.gestionUsers.model.Role;
import com.gestion.gestionUsers.repository.RoleRepository;
import com.gestion.gestionUsers.repository.UserRepository;
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
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/listroles")
    public String ShowListRole(Model model){
        model.addAttribute("roles", roleRepository.findAll());
        return "pages/tables/roles";
    
    }
    
    @GetMapping("/createRole")
    public String FormCreateRole(Model model){
        model.addAttribute("role", new Role());
        return "pages/forms/createrole";
    }


    @PostMapping("/createRole")
    public String addUser(@Valid Role role, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                model.addAttribute("errors", result.getAllErrors());
                return "/pages/forms/createrole";
                } else{
                    if (roleRepository.existsByName(role.getName())) {
                        model.addAttribute("message", role.getName());
                        return "/pages/forms/createrole";
                    }

                    roleRepository.save(role);
                    return "redirect:/listroles";
                    }
        } catch (Exception e) {
           
            e.getMessage();
        }
        return null;
    }

    @GetMapping("/editrole/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Role role = roleRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("role", role);
        //model.addAttribute("roles", roleRepository.findAll());
        return "pages/forms/editrole";
}

    @GetMapping("/deleterole/{id}")
    public String deleteRole(@PathVariable("id") long id, Model model) {
    Role role = roleRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

    // Vérifier si le rôle est assigné à des utilisateurs
    if (!userRepository.existsByRolesContains(role)) {
        // Aucun utilisateur n'a ce rôle, vous pouvez le supprimer
        roleRepository.delete(role);
        return "redirect:/listroles";

    } else {
        // Le rôle est assigné à des utilisateurs, afficher un message d'erreur
        model.addAttribute("error", "Cannot delete role. It is assigned to one or more users.");
        return "pages/samples/error";  
    }
      
    // roleRepository.delete(role);
}

}
