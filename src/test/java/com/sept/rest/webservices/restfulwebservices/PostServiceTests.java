package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.PostJpaRepository;
import com.sept.rest.webservices.restfulwebservices.post.PostRepository;
import com.sept.rest.webservices.restfulwebservices.post.PostService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PostServiceTests {

    public PostService postService;

    public PostRepository postRepository;

    List<Post> posts = new ArrayList<>();

    @BeforeEach
    public void initialise(){
        this.postRepository= Mockito.mock(PostRepository.class);
        this.postService = new PostService(postRepository);

        posts.add(new Post(1L, "test", "test description", new Date(), true));
        posts.add(new Post(2L, "test2", "test description", new Date(), false));
        posts.add(new Post(3L, "test3", "test description", new Date(), true));
        posts.add(new Post(4L, "test4", "test description", new Date(), false));
        posts.add(new Post(5L, "test5", "test description", new Date(), true));

    }

  @Test
    public void retrieveAllUsers() {
        DAOUser user = new DAOUser("test", "test", "test@email.com");
        List<DAOUser> userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
     public void retrieveAllPosts() {
        Mockito.when(postRepository.findAll()).thenReturn(posts);
        assertEquals(5, postService.retrieveAllPosts().size());
    }

   @Test
    public void retrieveByUsername() {

        String username = "test1";
        Mockito.when(postRepository.findByUsername(username)).thenReturn(posts);
        assertEquals(posts, postService.retrieveByUsername(username));

    }

    @Test
    public void retrieveById() {
        Long id = 1L;
        Mockito.when(postRepository.findById(id)).thenReturn(new Post());
        assertEquals(posts, postService.retrieveById(id));

    }

    @Test
    public void deleteById() {
        Post post = new Post(1L, "test", "test", new Date(), true);
        postService.DeleteById(post.getId());
        verify(postRepository, times(1)).deleteById(post.getId());
    }

    @Test
    public void submitPostsToDB(){
        Post post = new Post(1L, "test", "test", new Date(), true);
        postService.submitPostsToDB(post);
        verify(postRepository,times(1)).save(post);
    }

}
