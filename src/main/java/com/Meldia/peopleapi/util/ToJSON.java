package com.Meldia.peopleapi.util;

import java.util.Optional;

import org.json.JSONObject;

import com.Meldia.peopleapi.models.People;

public class ToJSON {

	public static JSONObject toJsonPerson(Optional<People> p) {
		JSONObject json = new JSONObject();
		json.put("first_name", p.get().getFirst_name());
		json.put("last_name", p.get().getLast_name());
		json.put("gender", p.get().getGender());
		json.put("birthday", p.get().getBirthday());
		json.put("nationality", p.get().getNationality());
		return json;
	}
	
	public static JSONObject toJsonObject(People p) {
		JSONObject json = new JSONObject();
		json.put("first_name", p.getFirst_name());
		json.put("last_name", p.getLast_name());
		json.put("gender", p.getGender());
		json.put("birthday", p.getBirthday());
		json.put("nationality", p.getNationality());
		return json;
	}
}
