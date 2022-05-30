package com.Meldia.peopleapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Meldia.peopleapi.dao.PeopleDAO;
import com.Meldia.peopleapi.models.People;
import com.Meldia.peopleapi.models.PeopleId;
import com.Meldia.peopleapi.models.PeopleUpdate;

@Component
public class PeopleService {

	@Autowired
	private PeopleDAO peopleDao;
	
	// FIND ALL
	public List<People> listPeople() {
		return peopleDao.findAll();
	}
	
	// FIND BY ID
	public Optional<People> searchPeople(PeopleId pId) {
		return peopleDao.findById(pId.getId());
	}
	
	// ADD NEW PERSON
	public void savePeople(People p) {
		peopleDao.save(p);
	}
	
	// UPDATE PERSON
	public void updatePeople(People p) {
		peopleDao.save(p);
	}
	
	public Optional<People> searchToUpdate(PeopleUpdate upd) {
		return peopleDao.findById(upd.getId());
	}
	
	// DELETE
	public void deletePeople(People p) {
		peopleDao.delete(p);
	}
}
