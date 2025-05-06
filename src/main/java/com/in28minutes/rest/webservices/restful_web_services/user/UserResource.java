package com.in28minutes.rest.webservices.restful_web_services.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	
	private UserDAOService service;
	
	public UserResource(UserDAOService service)
	{
		this.service=service;
	}
	
	@GetMapping("/users")
	public List<User> RetrieveAllUsers()
	{
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User RetrievUserById(@PathVariable Integer id)
	{
		return service.findUserById(id);
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user)
	{
		service.saveUser(user);
	}

}
