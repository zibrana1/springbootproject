/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ibrahim
 * 
 */
@Controller
public class WelcomeController {
    @Value("${welcome.message:test}")
    private String message = "Hello World";
    
    // @GetMapping("/")
    // public String welcome(Model model){
    //     //model.put("message", this.message);
    //     return "welcome";
    // }
    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "login";
    }
}
