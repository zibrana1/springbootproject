/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ibrahim
 */
@Controller
public class LoginController {

    @GetMapping("/home")
    public String welcomePage(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){

        return "pages/samples/login";
    }

    @GetMapping("/dashboard")
    public String welcomeDashboard(){
        return "pages/accueil";
    }

    @GetMapping("/table")
    public String PageAccueil() {
        
        return "pages/tables/basic-table";
    }


}
