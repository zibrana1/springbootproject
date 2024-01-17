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
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ibrahim
 */

@Controller

public class UserController {
    
    @Autowired
    
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/listusers")
    public String showUserList(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "pages/tables/users";
}
    
    @GetMapping("/adduser")
    public String showFormAddUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "pages/forms/createuser";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "layouts/adduser";
        }
        
        
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "layouts/update-user";
}
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, 
    BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "layouts/update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    return "redirect:/index";
}

}
