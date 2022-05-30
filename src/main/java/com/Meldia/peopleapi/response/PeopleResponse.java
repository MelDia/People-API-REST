package com.Meldia.peopleapi.response;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.Meldia.peopleapi.exception.ExceptionCustom;
import com.Meldia.peopleapi.models.People;
import com.Meldia.peopleapi.models.PeopleId;
import com.Meldia.peopleapi.models.PeopleUpdate;
import com.Meldia.peopleapi.service.PeopleService;
import com.Meldia.peopleapi.util.ToJSON;
import com.Meldia.peopleapi.util.UpdateValues;
import com.fasterxml.jackson.core.JsonProcessingException;

public class PeopleResponse {

	static Logger logger = LoggerFactory.getLogger(PeopleResponse.class);
	
	// FIND ALL
	public static String FindAllResp(PeopleService ps) throws JsonProcessingException  {
		List<People> listPeople = ps.listPeople();
		JSONObject json = new JSONObject();
		
		// IF THE DATA BASE IS EMPTY
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(listPeople.isEmpty()) return BadResponse.exceptionResp(json, HttpStatus.NOT_FOUND, new ExceptionCustom().dataBaseIsEmptyException());
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		json.put("people", listPeople);
		json.put("status", HttpStatus.OK.value());
		json.put("message", "All people are find");
		LogManager.getLogger().info("Response -> " + json);
		
		return json.toString();
	}
	
	// FIND BY ID
	public static String FindIdResp(Optional<People> p) throws JsonProcessingException {
		JSONObject json = new JSONObject();
		
		// IF THE PERSON DOES NOT FOUND
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(!(p.isPresent())) return BadResponse.exceptionResp(json, HttpStatus.NOT_FOUND, new ExceptionCustom().personNotFoundException());
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		json.put("person", ToJSON.toJsonPerson(p));
		json.put("status", HttpStatus.OK.value());
		json.put("message", "The person are find");
		LogManager.getLogger().info("Response -> " + json);
		
		return json.toString();
	}
	
	// ADD NEW PERSON
	public static String AddResp(People p, PeopleService ps) throws JsonProcessingException {
		JSONObject json = new JSONObject();
		
		// IF THE ATTRIBUTTES ARE NULL
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(p.getFirst_name() == null || (p.getFirst_name().trim()).equalsIgnoreCase("") ||
				p.getLast_name() == null ||  (p.getLast_name().trim()).equalsIgnoreCase("") ||
				p.getGender() == null || (p.getGender().trim()).equalsIgnoreCase("") ||
				p.getNationality() == null || (p.getNationality().trim()).equalsIgnoreCase("") ||
				p.getBirthday() == null || (p.getBirthday().trim()).equalsIgnoreCase("")) {
			return BadResponse.exceptionResp(json, HttpStatus.BAD_REQUEST, new ExceptionCustom().nullAttributteException());
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ps.savePeople(p);
		json.put("person", ToJSON.toJsonObject(p));
		json.put("status", HttpStatus.OK.value());
		json.put("message", "The person has been added");
		LogManager.getLogger().info("Response -> " + json);
		
		return json.toString();
	}
	
	// UPDATE PERSON
	public static String UpdateResp(Optional<People> p, PeopleUpdate upd, PeopleService ps) throws JsonProcessingException {
		JSONObject json = new JSONObject();
		
		// IF THE PERSON DOES NOT FOUND
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(!(p.isPresent())) return BadResponse.exceptionResp(json, HttpStatus.NOT_FOUND, new ExceptionCustom().personNotFoundException());
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		People updPeople = p.get();
		UpdateValues.setValues(updPeople, upd);
		ps.updatePeople(updPeople);
		json.put("person", ToJSON.toJsonObject(updPeople));
		json.put("status", HttpStatus.OK.value());
		json.put("message", "Data was modified/updated");
		LogManager.getLogger().info("Response -> " + json);
		
		return json.toString();
	}
	
	// DELETE
	public static String DeleteResp(Integer id, PeopleService ps) throws JsonProcessingException {
		PeopleId pId = new PeopleId();
		pId.setId(id);
		Optional<People> delete = ps.searchPeople(pId);
		
		JSONObject json = new JSONObject();
		
		// IF THE PERSON DOES NOT FOUND
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(!(delete.isPresent())) return BadResponse.exceptionResp(json, HttpStatus.NOT_FOUND, new ExceptionCustom().personNotFoundException());
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ps.deletePeople(delete.get());
		json.put("person", ToJSON.toJsonPerson(delete));
		json.put("status", HttpStatus.OK.value());
		json.put("message", "Data has been deleted");
		LogManager.getLogger().info("Response -> " + json);
		
		return json.toString();
	}
}
