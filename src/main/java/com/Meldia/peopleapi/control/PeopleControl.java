package com.Meldia.peopleapi.control;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Meldia.peopleapi.models.People;
import com.Meldia.peopleapi.models.PeopleId;
import com.Meldia.peopleapi.models.PeopleUpdate;
import com.Meldia.peopleapi.response.PeopleResponse;
import com.Meldia.peopleapi.service.PeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("people")
public class PeopleControl {

	@Autowired
	private PeopleService ps;
	
	// FIND ALL
	@RolesAllowed("USER")
	@RequestMapping(value = "/all", produces = {"application/json"}, method = RequestMethod.GET)
	public String allPeople() throws JsonProcessingException {
		return PeopleResponse.FindAllResp(ps);
	}
	
	// FIND BY ID
	@RolesAllowed("USER")
	@RequestMapping(value = "/search/{id}", produces = {"application/json"}, method = RequestMethod.GET)
	public String findById(@PathVariable("id") Integer id)  throws JsonProcessingException {
		Optional<People> p = ps.searchPeople(new PeopleId(id));
		return PeopleResponse.FindIdResp(p);
	}
	
	// ADD NEW 
	@Secured("ADMIN")
	@RequestMapping(value = "/add", produces = {"application/json"}, method = RequestMethod.POST)
	public String addPeople(@RequestBody People p) throws JsonProcessingException {
		return PeopleResponse.AddResp(p, ps);
	}
	
	// MODIFIED/UPDATED DATA 
	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/update", produces = {"application/json"}, method = RequestMethod.PUT)
	public String updatePeople(@RequestBody PeopleUpdate upd) throws JsonProcessingException {
		Optional<People> p = ps.searchToUpdate(upd);
		return PeopleResponse.UpdateResp(p, upd, ps);
	}
	
	// DELETE
	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/delete/{id}", produces = {"application/json"}, method = RequestMethod.DELETE)
	public String updatePeople(@PathVariable("id") Integer id)  throws JsonProcessingException {
		return PeopleResponse.DeleteResp(id, ps);
	}
}
