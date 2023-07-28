package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.PostDTO;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public List<PostDTO> getPostFeed(@PathVariable Long id) {
        return postService.getPostsFeed(id);
    }

    @GetMapping("/start={text}")
    public List<PostDTO> getPostsStartsWith(@PathVariable String text) {
        return postService.searchPostsStartsWith(text);
    }

    @DeleteMapping("/remove_loser")
    public void removeLoser(){
        postService.removeFirstPostWithMinAmountOfLikes();
    }



}
