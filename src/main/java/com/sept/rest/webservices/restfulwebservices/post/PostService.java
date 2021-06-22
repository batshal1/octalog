package com.sept.rest.webservices.restfulwebservices.post;


import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import com.sept.rest.webservices.restfulwebservices.model.UserJpaRepository;
import com.sept.rest.webservices.restfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

     public PostService(PostRepository postRepository){
    this.postRepository = postRepository;
    }



    public List<DAOUser> retrieveAllUsers(){
        List<DAOUser> userList = userRepository.findAll();
        return userList;
    }

    public List<Post> retrieveAllPosts(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public List<Post> retrieveByUsername(String username){
        List<Post> listFromUsername = postRepository.findByUsername(username);
        return listFromUsername;
    }

    public Post retrieveById(Long id){
        return postRepository.findById(id);
    }

    public void DeleteById(Long id){
        postRepository.deleteById(id);
    }

    public Post submitPostsToDB(Post post){
        return postRepository.save(post);
    }

    public List<PostComment> retrieveCommentsById(Long id){
        List<PostComment> commentList = postRepository.findById(id).getComments();
        return commentList;
    }

    public Post submitComment(Long id, PostComment comment){
        return postRepository.findById(id).addComment(comment);
    }

}
