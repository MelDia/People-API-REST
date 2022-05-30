package com.Meldia.peopleapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeopleId {
	
	@JsonProperty("id")
	private Integer id;

	public PeopleId() {	}

	public PeopleId(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
