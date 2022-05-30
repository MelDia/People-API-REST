package com.Meldia.peopleapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Meldia.peopleapi.models.People;

public interface PeopleDAO extends JpaRepository<People, Integer> {

}
