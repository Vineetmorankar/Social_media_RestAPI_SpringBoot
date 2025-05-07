package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

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
		User user = service.findUserById(id);
		if(user==null) throw new userNotFoundException("id:"+id);
		return service.findUserById(id);
	}
	
	@DeleteMapping("/users/{id}")
	public void DeleteUser(@PathVariable Integer id)
	{
		service.DeleteUserById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user)
	{
		User saveduser = service.saveUser(user);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
