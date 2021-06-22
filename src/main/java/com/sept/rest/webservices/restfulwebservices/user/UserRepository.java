package com.sept.rest.webservices.restfulwebservices.user;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;

import java.util.List;

public interface UserRepository {

    List<DAOUser> findAll();
    List<DAOUser> findByUsername(String username);
}
