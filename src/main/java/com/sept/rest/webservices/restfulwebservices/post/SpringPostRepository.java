package com.sept.rest.webservices.restfulwebservices.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringPostRepository implements PostRepository{

    @Autowired
    PostJpaRepository postJpaRepository;


    @Override
    public List<Post> findAll() {
        return postJpaRepository.findAll();
    }

    @Override
    public List<Post> findByUsername(String username) {
        return postJpaRepository.findByUsername(username);
    }

    @Override
    public Post findById(Long id) {
        return postJpaRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        postJpaRepository.deleteById(id);
    }

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(post);
    }
}
