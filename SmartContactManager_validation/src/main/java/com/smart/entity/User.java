package com.smart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="UserTable")
public class User{

	//properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message ="Name must required !!")
	@Size(min = 2,max = 20,message = "minimum 2 or maximum 20 characters are allowed !!")
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String imgUrl;
	private boolean enable;
	
	@Column(length = 500)
	private String about;
	
	//mapping
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<Contact> contact=new ArrayList<>();

	//toString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emil=" + email + ", password=" + password + ", role=" + role
				+ ", imgUrl=" + imgUrl + ", enable=" + enable + ", about=" + about + "]";
	}
	
	
}//class
