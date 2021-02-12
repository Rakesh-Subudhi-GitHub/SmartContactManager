package com.smart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore  //just bcz its serialize the user then go user class then serialize the all entity 
								//this user class contact class there then come here its create a loop so
	private User user;

	//toString
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", secondName=" + secondName + ", work=" + work + ", email="
				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + "]";
	}
	
	
}//class
