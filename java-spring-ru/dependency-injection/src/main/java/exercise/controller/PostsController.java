package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;


import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    @GetMapping(path = "/{id}")
    public Post getPost(@PathVariable long id) {
        return postRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Post update(@PathVariable long id, @RequestBody Post data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setBody(data.getBody());
        post.setCreatedAt(data.getCreatedAt());
        post.setTitle(data.getTitle());
        postRepository.save(post);
        return post;
    }

    @GetMapping(path = "")
    public Iterable<Post> getPosts() {
        return this.postRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable long id) {
        this.commentRepository.deleteByPostId(id);
        this.postRepository.deleteById(id);
    }

}
// END
