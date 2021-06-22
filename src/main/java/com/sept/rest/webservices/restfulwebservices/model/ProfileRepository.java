package com.sept.rest.webservices.restfulwebservices.model;

import java.util.List;

public interface ProfileRepository {

    Profile findByUsername(String username);
    List<Profile> findAll();
}
