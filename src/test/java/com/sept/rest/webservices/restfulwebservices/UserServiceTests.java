package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.model.Profile;
import com.sept.rest.webservices.restfulwebservices.model.ProfileJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private ProfileJpaRepository profileJpaRepository;

    public void retrieveAvatarLink(){
        String username = "test";

    }

    @Test
    public void retrieveAllProfile() {
        Profile profile = new Profile(1L, "test", "test", "test","test@email.com", "+123456789","aboutMe test","link","link");
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);

        when(profileJpaRepository.findAll()).thenReturn(profiles);
        assertEquals(1, userService.retrieveAllProfile().size());
    }
}
