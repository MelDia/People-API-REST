package com.Meldia.peopleapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.Meldia.peopleapi.security.interceptor.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private static String REALM = "people-api";
	
	// AUTHENTICATION
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("MELDIA|User=ADMIN").password("{noop}MELDIA|Pass=ADMIN").roles("ADMIN")
                .and()
                .withUser("MELDIA|User=USER").password("{noop}MELDIA|Pass=USER").roles("USER");
    }

	// AUTHORIZATION
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/people/all").access("hasAnyRole('ADMIN', 'USER')")
        .antMatchers("/people/search/{id}").access("hasAnyRole('ADMIN', 'USER')")
        .antMatchers("/people/add").access("hasRole('ADMIN')")
        .antMatchers("/people/update").access("hasRole('ADMIN')")
        .antMatchers("/people/delete/{id}").access("hasRole('ADMIN')")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
	
	@Autowired
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
