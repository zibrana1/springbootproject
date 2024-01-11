/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.gestionUsers.model;

import java.util.List;
import javax.persistence.*;


/**
 *
 * @author ibrahim
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name ="id", length=11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    
    @Basic
    @Column(length = 45)
	private String firstname ;

	@Basic
	@Column(length = 45)
	private String lastname;

	@Basic
	@Column(length = 60, nullable = false)
	private String email;

	@Basic
	@Column(length = 8)
	private Integer phoneNumber;

    @Basic
    @Column(length = 45, nullable = false)
    private String username;


    @Basic
    @Column(length=65, nullable = false, unique=true)
    private String password;
    
    @Basic
    @Column(nullable=true)
    private Boolean enabled;
    
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;
    
    
    
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

    public User(String firstname, String lastname, String email, Integer phoneNumber, String username, String password, Boolean enabled, List<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

	public User(String username, String password, String firstname, String lastname, Integer phoneNumber, String email){
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.email = email;
        this.enabled = false;
	}

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
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
