package com.Meldia.peopleapi.response;

import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import com.Meldia.peopleapi.exception.ExceptionAPI;
import com.fasterxml.jackson.core.JsonProcessingException;

public class BadResponse {

	public static String exceptionResp(JSONObject json, HttpStatus http, ExceptionAPI ex) throws JsonProcessingException {
		json.put("status", http.value());
		json.put("ErrorCode", ex.getErrorCode());
		json.put("ErrorMessage", ex.getErrorMessage());
		
		LogManager.getLogger().info(json);
		return json.toString();
	}

	public static String exceptionAuthorization(JSONObject json,AuthenticationException exception, Integer status) {
		json.put("ErrorCode", "AUTH-0001");
		json.put("status", status);
		json.put("ErrorMessage", exception.getMessage());		
		LogManager.getLogger().info(json);
		return json.toString();
	}
	
	
	//public static String exceptionAuthorization(JSONObject json,AuthenticationException exception, Integer status) {
		//json.put("ErrorCode", "AUTH-0001");
		//json.put("status", status);
		//json.put("ErrorMessage", exception.getMessage());		
		//LogManager.getLogger().info(json);
		//return json.toString();
	//}
}
