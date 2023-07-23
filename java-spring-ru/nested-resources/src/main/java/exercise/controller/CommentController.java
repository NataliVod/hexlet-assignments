package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN

    @GetMapping(path = "{postId}/comments")
    public Iterable<Comment> getAllCommentsForPost(@PathVariable("postId") Long postId) {

        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "{postId}/comments/{commentId}")
    public Comment getCommentForPost(@PathVariable("commentId") Long commentId,
                              @PathVariable("postId") Long postId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "{postId}/comments")
    public Iterable<Comment> createCommentForPost(@PathVariable("postId") Long postId,
                                           @RequestBody CommentDto commentDto) {

        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(commentDto.content());
        commentRepository.save(comment);
        return commentRepository.findAllById(Collections.singleton(postId));
    }

    @PatchMapping(path = "{postId}/comments/{commentId} ")
    public void updateComment(@PathVariable("postId") Long postId,
                                 @PathVariable("commentId") Long commentId,
                                 @RequestBody CommentDto commentDto) {

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDto.content());
        commentRepository.save(comment);
    }

    @DeleteMapping(path = "{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId) {

       Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }

    // END
}
