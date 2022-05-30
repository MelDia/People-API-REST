package com.Meldia.peopleapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "people")
public class People implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "id_people")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "first_name")
	private String first_name;
	
	@Column (name = "last_name")
	private String last_name;
	
	@Column (name = "gender")
	private String gender;
	
	@Column (name = "birthday")
	private String birthday;
	
	@Column (name = "nationality")
	private String nationality;

	public Integer getId_people() {
		return id;
	}

	public void setId_people(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "People [id:" + id 
				+ ", first name:" + first_name 
				+ ", last name:" + last_name 
				+ ", gender:" + gender
				+ ", birthday:" + birthday 
				+ ", nationality:" + nationality 
				+ "]";
	}
	
	

}
