package com.sept.rest.webservices.restfulwebservices.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.model.Profile;


@CrossOrigin(origins="http://localhost:4200")
@RestController

public class UserController {

    @Autowired
	private UserService userService;
	
	@GetMapping("/jpa/users/{username}/profile")
	public List<DAOUser> getUser(@PathVariable String username){
		return userService.retrieveUsersByUsername(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/profile/avatar")
	public String getAvatarLink(@PathVariable String username){
		return userService.retrieveAvatarLink(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/profile/background")
	public String getBackgroundLink(@PathVariable String username){
		return userService.retrieveBackgroundLink(username);
		//return todoService.findAll();
	}
	
	
	@GetMapping("/jpa/users/{username}/profile/details")
	public Profile getProfileDetails(@PathVariable String username) {
		return userService.retrieveProfileDetails(username);
	}

	@GetMapping("/jpa/users/all/profile")
	public List<Profile> getAll(){
		return userService.retrieveAllProfile();
	}

}