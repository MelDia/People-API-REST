package com.Meldia.peopleapi.security;

public interface AuthService {

	Boolean validateBasicAuthentication(String username, String password, String basicAuthHeaderValue);
}
