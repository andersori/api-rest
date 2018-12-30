package com.dvn.core.model;

import java.time.LocalDateTime;

public class Account {
	
	private Long id;
	private String username;
	private String name;
	private String password;
	private LocalDateTime createdOn;
	private LocalDateTime lastLogin;
	
	public Account() {
		this.setCreatedOn(LocalDateTime.now());
	}
	
	public Account(String username, String name, String password) {
		this.setUsername(username);
		this.setName(name);
		this.setPassword(password);
		this.setCreatedOn(LocalDateTime.now());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}
