package com.sept.rest.webservices.restfulwebservices.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringProfileRepository implements ProfileRepository {

    @Autowired
    ProfileJpaRepository profileJpaRepository;


    @Override
    public Profile findByUsername(String username) {
        return profileJpaRepository.findByUsername(username);
    }

    @Override
    public List<Profile> findAll() {
        return profileJpaRepository.findAll();
    }
}
