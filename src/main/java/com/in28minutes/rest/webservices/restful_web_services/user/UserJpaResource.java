package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;
import com.in28minutes.rest.webservices.restful_web_services.post.Post;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserJpaResource {

	
	private UserDAOService service;
	
	private UserRepository userRepository;
	
	public UserJpaResource(UserDAOService service, UserRepository userRepository)
	{
		this.service=service;
		this.userRepository=userRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> RetrieveAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> RetrievUserById(@PathVariable Integer id)
	{
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) throw new userNotFoundException("id:"+id);
		
		EntityModel<User> entitymodel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).RetrieveAllUsers());
		entitymodel.add(link.withRel("all-usrs"));
		
		return entitymodel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void DeleteUser(@PathVariable Integer id)
	{
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> FindUserPosts(@PathVariable Integer id)
	{	
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) throw new userNotFoundException("id:"+id);
		
		return user.get().getPost();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user)
	{
		User saveduser = userRepository.save(user);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
