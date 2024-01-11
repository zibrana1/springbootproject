/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import com.gestion.gestionUsers.model.User;
import com.gestion.gestionUsers.repository.RoleRepository;
import com.gestion.gestionUsers.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ibrahim
 */

@Controller

public class UserController {
    
    @Autowired
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @GetMapping("/index")
    public String showUserList(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "layouts/users";
}
    
    @GetMapping("/adduser")
    public String showFormAddUser(Model model){
        model.addAttribute("user", new User());
        return "layouts/adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "layouts/adduser";
        }
        
        userRepository.save(user);
        return "redirect:/index";
    }
    
}
