package com.sept.rest.webservices.restfulwebservices.user;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.model.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringUserRepository implements UserRepository{

    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public List<DAOUser> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public List<DAOUser> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }
}
