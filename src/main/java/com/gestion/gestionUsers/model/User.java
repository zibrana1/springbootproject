/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 *
 * @author ibrahim
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name ="user_id", length=11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    
    @Basic
    @Column(length = 45, nullable = false)
    @NotBlank(message="feild firstname is required")
    private String firstname ;

    @Basic
    @NotBlank(message="feild lasttname is required")
    @Column(length = 45, nullable = false)
	private String lastname;

	@Basic
	@Column(length = 60, nullable = false)
    @NotBlank(message="Field email is required")
    @Email(message="Email is not valid")
	private String email;

	@Basic
	@Column(length = 13)
    @NotBlank(message="Field phone number is required")
    @Pattern(regexp = "^\\+228[2-9]\\d{7}$", message = "Phone number is not valid")
     private String phoneNumber;

    @Basic
    @Column(length = 45, nullable = false)
    @NotBlank(message="Field username is required")
    private String username;


    @Basic
    @Column(length=65, nullable = false, unique=true)
    @NotBlank(message="Field password is required")
    private String password;
    
    @Basic
    @Column(nullable=true)
    private Boolean enabled=false;
    
    @ManyToMany
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();
    
    
    @Transient
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    
    
    public User() {

	}
	public User(String username) {
		super();
		this.username = username;
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

    

	public User(String username, String password, Boolean enabled) {
		this.username= username;
		this.password = password;
        this.enabled =false;
	}

    public User(String firstname, String lastname, String email, String phoneNumber, String username, String password, Boolean enabled, List<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

	public User(String username, String password, String firstname, String lastname, String phoneNumber, String email){
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.email = email;
        this.enabled = false;
	}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

   

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	

	@Override
	public String toString() {
		return "Username=" + username + "enabled :" + enabled;
	}
    
}
