package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerVersioning {

	@GetMapping("/v1/person")
	public Personv1 getfullname()
	{
		return new Personv1("Vineet Morankar");
	}
	
	@GetMapping("/v2/person")
	public Personv2 getBreakedname()
	{
		return new Personv2(new Name("Vineet", "Morankar"));
	}
	
	@GetMapping(path="/person", params="version=1")
	public Personv1 getfullnameRequestParam()
	{
		return new Personv1("Vineet Morankar");
	}
	
	@GetMapping(path="/person", params="version=2")
	public Personv2 getBreakednameRequestParam()
	{
		return new Personv2(new Name("Vineet", "Morankar"));
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public Personv1 getfullnameRequestHeader()
	{
		return new Personv1("Vineet Morankar");
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public Personv2 getBreakednameRequestHeader()
	{
		return new Personv2(new Name("Vineet", "Morankar"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public Personv1 getfullnameRequestMedia()
	{
		return new Personv1("Vineet Morankar");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public Personv2 getBreakednameRequestMedia()
	{
		return new Personv2(new Name("Vineet", "Morankar"));
	}
}
