package com.in28minutes.rest.webservices.restful_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringControler {

//	@GetMapping("/filtering")
//	public Sombean staticfiltering()
//	{
//		return new Sombean("value1", "value2", "value3");
//	}
//	
//	@GetMapping("/filtering-list")
//	public List<Sombean> staticfilteringlist()
//	{
//		return Arrays.asList(new Sombean("value1", "value2", "value3"), new Sombean("value4", "value5", "value6"));
//	}
	
	@GetMapping("/filtering")
	public MappingJacksonValue dynamicfiltering()
	{
		Sombean somebean = new Sombean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue dynamicfilteringlist()
	{
		List<Sombean> list =  Arrays.asList(new Sombean("value1", "value2", "value3"), new Sombean("value4", "value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
