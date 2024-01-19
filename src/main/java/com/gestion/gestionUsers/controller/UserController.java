/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import com.gestion.gestionUsers.model.Role;
import com.gestion.gestionUsers.model.User;
import com.gestion.gestionUsers.repository.RoleRepository;
import com.gestion.gestionUsers.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        
        try {
            if (result.hasErrors()) {
                model.addAttribute("errors", result.getAllErrors());
                return "/pages/forms/createuser";
                } else{
                    userRepository.save(user);
                    return "redirect:/listusers";
                    }
        } catch (Exception e) {
           
            e.getMessage();
        }
        return null;
    }

    @GetMapping("/edituser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "pages/forms/edituser";
}
    
    @PostMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") long user_id, @Valid User user, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            user.setUser_id(user_id);
            return "pages/forms/edituser";
        }



        userRepository.save(user);
        return "redirect:/listusers";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    return "redirect:/listusers";
}

}
