package com.sept.rest.webservices.restfulwebservices.user;

import com.sept.rest.webservices.restfulwebservices.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;


    public List<DAOUser> retrieveUsersByUsername(String username){
        List<DAOUser> userList = userRepository.findByUsername(username);
        return userList;
    }

    public String retrieveAvatarLink(String username){

        return profileRepository.findByUsername(username).getAvatar();
    }

    public String retrieveBackgroundLink(String username){
        return profileRepository.findByUsername(username).getBackground();
    }

    public Profile retrieveProfileDetails(String username){
        return profileRepository.findByUsername(username);
    }

    public List<Profile> retrieveAllProfile(){
        List<Profile> profileList = profileRepository.findAll();
        return profileList;
    }

}
