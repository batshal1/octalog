package com.owlsden.Server.user;

import com.owlsden.Server.model.DAOUser;
import com.owlsden.Server.model.Profile;
import com.owlsden.Server.model.ProfileRepository;
import com.owlsden.Server.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins="http://localhost:4200")
@RestController

public class UserJpaResource {
    @Autowired
	private UserRepository UserRepository;

    @Autowired
    private ProfileRepository profileRepository;
	
	@GetMapping("/jpa/users/{username}/profile")
	public List<DAOUser> getUser(@PathVariable String username){
		return UserRepository.findByUsername(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/profile/avatar")
	public String getAvatarLink(@PathVariable String username){
		return profileRepository.findByUsername(username).getAvatar();
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/profile/background")
	public String getBackgroundLink(@PathVariable String username){
		return profileRepository.findByUsername(username).getBackground();
		//return todoService.findAll();
	}
	
	
	@GetMapping("/jpa/users/{username}/profile/details")
	public Profile getProfileDetails(@PathVariable String username) {
		return profileRepository.findByUsername(username);
	}

	@GetMapping("/jpa/users/all/profile")
	public List<Profile> getAll(){
		return profileRepository.findAll();
	}

}