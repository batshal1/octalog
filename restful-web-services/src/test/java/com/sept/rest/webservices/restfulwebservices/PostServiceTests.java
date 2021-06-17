package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.PostJpaRepository;
import com.sept.rest.webservices.restfulwebservices.post.PostService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTests {

    @Autowired
    public PostService postService;

    @MockBean
    public PostJpaRepository postJpaRepository;

  @Test
    public void retrieveAllUsers() {
        DAOUser user = new DAOUser("test", "test", "test@email.com");
        List<DAOUser> userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
     public void retrieveAllPosts() {
        Post post = new Post(1L, "test", "test description", new Date(), true);
        List<Post> postList = new ArrayList<>();
        postList.add(post);

        when(postJpaRepository.findAll()).thenReturn(postList);
        assertEquals(1, postService.retrieveAllPosts().size());
    }

   @Test
    public void retrieveByUsername() {
        String username = "test";
        Post post = new Post(1L, "test", "test", new Date(), true);
        Post post1 = new Post(2L, "test1", "test1", new Date(), false);
        Post post2 = new Post(3L, "test2", "test2", new Date(), false);
        Post post3 = new Post(4L, "test3", "test2", new Date(), false);
        List<Post> postList = new ArrayList<>();
        postList.add(post);
        postList.add(post1);
        postList.add(post2);
        postList.add(post3);

        when(postJpaRepository.findByUsername(username)).thenReturn(postList);
        assertEquals(postList, postService.retrieveByUsername(username));

    }

    @Test
    public void retrieveById() {
        Long id = 1L;
        Post post = new Post(1L, "test", "test", new Date(), true);
        when(postJpaRepository.findById(id)).thenReturn(Optional.of(post));
        assertEquals(post, postService.retrieveById(id));

    }

    @Test
    public void deleteById() {
        Post post = new Post(1L, "test", "test", new Date(), true);
        postService.DeleteById(post.getId());
        verify(postJpaRepository, times(1)).deleteById(post.getId());
    }

    @Test
    public void submitPostsToDB(){
        Post post = new Post(1L, "test", "test", new Date(), true);
        postService.submitPostsToDB(post);
        verify(postJpaRepository,times(1)).save(post);
    }

}
