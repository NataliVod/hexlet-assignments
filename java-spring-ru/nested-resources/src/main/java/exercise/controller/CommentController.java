package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "{postId}/comments")
    public Iterable<Comment> getAllCommentsForPost(@PathVariable(name = "postId") Long postId) {

        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "{postId}/comments/{commentId}")
    public Comment getCommentForPost(@PathVariable(name = "commentId") Long commentId,
                                     @PathVariable(name = "postId") Long postId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "{postId}/comments")
    public void createCommentForPost(@PathVariable(name = "postId") Long postId,
                                     @RequestBody CommentDto commentDto) {

        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(commentDto.content());
        commentRepository.save(comment);
    }

    @PatchMapping(path = "{postId}/comments/{commentId} ")
    public void updateComment(@PathVariable(name = "postId") Long postId,
                              @PathVariable(name = "commentId") Long commentId,
                              @RequestBody CommentDto commentDto) {

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDto.content());
        commentRepository.save(comment);
    }

    @DeleteMapping(path = "{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable(name = "postId") Long postId,
                              @PathVariable(name = "commentId") Long commentId) {

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
    // END
}
