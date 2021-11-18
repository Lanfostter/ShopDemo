package com.example.ShopDemo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "u_id")
	private int id;
	@Column(name = "u_name")
	private String name;
	@Column(name = "u_birthday")
	private Date birthday;
	@Column(name = "u_username")
	private String username;
	@Column(name = "u_password")
	private String password;
	@Column(name = "u_role")
	private String role;
	@Column(name = "u_avatar")
	private String avatar;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public UserEntity(int id, String name, Date birthday, String username, String password, String role,
			String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.role = role;
		this.avatar = avatar;
	}
	public UserEntity() {
		super();
	}
	
}
