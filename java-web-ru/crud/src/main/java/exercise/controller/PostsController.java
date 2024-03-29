package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

        var per = 5;
        var firstPost = (pageNumber - 1) * per  ;
        List<Post> pagedPosts;

        try {
           pagedPosts = posts.subList(firstPost, firstPost + per);
        } catch (IndexOutOfBoundsException e) {
           pagedPosts = new ArrayList<>();
        }

        var page = new PostsPage(pagedPosts, pageNumber);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id);
        if (post == null)
            throw new NotFoundResponse("Page not found");

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
