package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	MessageSource messageSource;
	
	
	public HelloWorldController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@GetMapping(path="/hello-world")
	public String helloWorld()
	{
		return "Hello World Rest";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldbean()
	{
		return new HelloWorldBean("Hello World Rest");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorlPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World Rest %s", name));
	}
	
	@GetMapping(path="/hello-world-international")
	public String helloWorldInternationalization()
	{
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default", locale );
	}
}
