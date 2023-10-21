package exercise.controller.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RequestMapping("/api")
@RestController
public class PostsController {

    private List<Post> posts = Data.getPosts();


    @PostMapping("users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post data, @PathVariable int id) {
        Post post = new Post();
        post.setUserId(id);
        post.setSlug(data.getSlug());
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        posts.add(post);
        return post;
    }

    @GetMapping("users/{id}/posts")
    public List<Post> showPostsByUser(@PathVariable int id) {
        var postsByUser = posts.stream()
                .filter(p -> p.getUserId() == id)
                .collect(Collectors.toList());
        return postsByUser;
    }
}
// END
