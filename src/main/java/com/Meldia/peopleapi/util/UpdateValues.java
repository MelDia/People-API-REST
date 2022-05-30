package com.Meldia.peopleapi.util;

import com.Meldia.peopleapi.models.People;
import com.Meldia.peopleapi.models.PeopleUpdate;

public class UpdateValues {
	
	public static void setValues(People p, PeopleUpdate upd) {
		
		if (upd.getFirst_name() != null) {p.setFirst_name(upd.getFirst_name());}
		
		if (upd.getLast_name() != null) {p.setLast_name(upd.getLast_name());}
		
		if (upd.getGender() != null) {p.setGender(upd.getGender());}
		
		if(upd.getBirthday() != null) {p.setBirthday(upd.getBirthday());}
		
		if(upd.getNationality() != null) {p.setNationality(upd.getNationality());}
    }
}
