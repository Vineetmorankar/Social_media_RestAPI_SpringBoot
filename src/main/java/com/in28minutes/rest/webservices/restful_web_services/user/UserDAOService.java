package com.in28minutes.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static int userCount=0;
	
	static {
		users.add(new User(++userCount,"Vineet",LocalDate.now().minusYears(22)));
		users.add(new User(++userCount,"Pratik",LocalDate.now().minusYears(23)));
		users.add(new User(++userCount,"Aniket",LocalDate.now().minusYears(21)));
	}
	
	public List<User> findAll()
	{
		return users;
	}

	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setId(++userCount);
		users.add(user);
		return user;
	}
}
