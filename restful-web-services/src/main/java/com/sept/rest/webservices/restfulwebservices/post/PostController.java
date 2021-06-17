package com.sept.rest.webservices.restfulwebservices.post;

import java.net.URI;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.model.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/jpa/users/")
	public List<DAOUser> getAllUser() {
		return postService.retrieveAllUsers();
	}
	@GetMapping("/jpa/users/posts")
	public List<Post> getAll() {
		return postService.retrieveAllPosts();
	}

	@GetMapping("/jpa/users/{username}/posts")
	public List<Post> getAllTodos(@PathVariable String username){
		return postService.retrieveByUsername(username);
	}

	@GetMapping("/jpa/users/{username}/posts/{id}")
	public Post getTodo(@PathVariable String username, @PathVariable long id){
		return postService.retrieveById(id);
	}

	@GetMapping("/jpa/users/{username}/posts/{id}/comments")
	public List<PostComment> getComments(@PathVariable String username, @PathVariable long id){
		return postService.retrieveCommentsById(id);
	}

	@PostMapping("/jpa/users/{username}/posts/{id}/comments")
	public ResponseEntity<Void> PostMapping(@PathVariable String username, @PathVariable long id, @RequestBody PostComment comment){

		Post updatedPost = postService.submitComment(id,comment);
		updatedPost.setUsername(username);

		postService.submitPostsToDB(updatedPost); // here lies the problem
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/jpa/users/{username}/posts/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		postService.DeleteById(id);
		return ResponseEntity.noContent().build();
	}
	

	@PutMapping("/jpa/users/{username}/posts/{id}")
	public ResponseEntity<Post> updateTodo(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Post post){
		
		post.setUsername(username);
		post.setComments(postService.retrieveCommentsById(id));
		Post postUpdated = postService.submitPostsToDB(post);
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/posts")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Post post){
		
		post.setUsername(username);

		Post createdPost = postService.submitPostsToDB(post);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdPost.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
