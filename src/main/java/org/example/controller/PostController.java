package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.exception.PostNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.model.dto.CommentDTORecord;
import org.example.model.dto.PostDTORecord;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public List<PostDTORecord> getPostFeed(@PathVariable Long id) {
        try {
            return postService.getPostsFeed(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/{userId}/post={postId}")
    public PostDTORecord getPostOfUser(@PathVariable Long userId, @PathVariable Integer postId) {
        try {
            return postService.getPostOfUser(userId, postId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/start={text}")
    public List<PostDTORecord> getPostsStartsWith(@PathVariable String text) {
        return postService.searchPostsStartsWith(text);
    }

    @DeleteMapping("/remove_loser")
    public void removeLoser(){
        postService.removeFirstPostWithMinAmountOfLikes();
    }

    @PostMapping("/{id}/add_comment")
    public void addComment(@PathVariable  Integer id,
                           @RequestBody String comment)
    {
        try {
            postService.addComment(id, comment);
        } catch (PostNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id}/comments")
    public List<CommentDTORecord> getCommentsOfPost(@PathVariable Integer id){
        try {
            return postService.getCommentsOfPost(id);
        } catch (PostNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
