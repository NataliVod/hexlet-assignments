package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        var result = posts.stream().limit(limit).toList();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(result);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post)  {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping("/posts/{slug}")
    public ResponseEntity<Post> update(@PathVariable String slug, @RequestBody Post data) {
        Optional<Post> maybePost = posts.stream()
                .filter(p -> p.getSlug().equals(slug))
                .findFirst();
        if (maybePost.isPresent()) {
            Post post = maybePost.get();
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/posts/{slug}")
    public ResponseEntity<Post> show(@PathVariable String slug) {
        var post = posts.stream()
                .filter(p -> p.getSlug().equals(slug))
                .findFirst();
        return ResponseEntity.of(post);
    }
    // END

    @DeleteMapping("/posts/{slug}")
    public void destroy(@PathVariable String slug) {
        posts.removeIf(p -> p.getSlug().equals(slug));
    }
}
