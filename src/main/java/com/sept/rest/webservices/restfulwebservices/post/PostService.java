package com.sept.rest.webservices.restfulwebservices.post;


import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostJpaRepository postJpaRepository;

    @Autowired
    UserRepository userRepository;

    public List<DAOUser> retrieveAllUsers(){
        List<DAOUser> userList = userRepository.findAll();
        return userList;
    }

    public List<Post> retrieveAllPosts(){
        List<Post> postList = postJpaRepository.findAll();
        return postList;
    }

    public List<Post> retrieveByUsername(String username){
        List<Post> listFromUsername = postJpaRepository.findByUsername(username);
        return listFromUsername;
    }

    public Post retrieveById(Long id){
        return postJpaRepository.findById(id).orElse(null);
    }

    public void DeleteById(Long id){
        postJpaRepository.deleteById(id);
    }

    public Post submitPostsToDB(Post post){
        return postJpaRepository.save(post);
    }

    public List<PostComment> retrieveCommentsById(Long id){
        List<PostComment> commentList = postJpaRepository.findById(id).get().getComments();
        return commentList;
    }

    public Post submitComment(Long id, PostComment comment){
        return postJpaRepository.findById(id).get().addComment(comment);
    }

}
