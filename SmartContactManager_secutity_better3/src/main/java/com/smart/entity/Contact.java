package com.smart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contact{

	//properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	
	private String name;
	private String secondName;
	private String work;
	private String email;
	private String phone;
	private String image;
	
	@Column(length = 500)
	private String description;
	
	//mapping
	@ManyToOne
	private User user;

	//toString
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", secondName=" + secondName + ", work=" + work + ", email="
				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + "]";
	}
	
	
}//class
