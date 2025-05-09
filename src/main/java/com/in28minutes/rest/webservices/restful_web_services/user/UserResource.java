package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
	public EntityModel<User> RetrievUserById(@PathVariable Integer id)
	{
		User user = service.findUserById(id);
		if(user==null) throw new userNotFoundException("id:"+id);
		
		EntityModel<User> entitymodel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).RetrieveAllUsers());
		entitymodel.add(link.withRel("all-usrs"));
		
		return entitymodel;
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
