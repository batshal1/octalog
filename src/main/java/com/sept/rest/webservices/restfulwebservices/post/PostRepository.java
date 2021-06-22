package com.sept.rest.webservices.restfulwebservices.post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();
    List<Post> findByUsername(String username);
    Post findById(Long id);
    void deleteById(Long id);
    Post save(Post post);
}
