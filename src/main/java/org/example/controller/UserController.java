package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.exception.UserNotFoundException;
import org.example.model.dto.PostDTORecord;
import org.example.model.dto.ProfileDTORecord;
import org.example.model.dto.UserDTORecord;
import org.example.service.PostService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping
    public List<UserDTORecord> list() {
        return userService.list();
    }

    @GetMapping("public/most_popular_user")
    public UserDTORecord getMostPopularUser() {
        return userService.getMostPopularUser();
    }

    @GetMapping("public/most_popular_user/profile")
    public ProfileDTORecord getProfileOfMostPopularUser() throws ExecutionException, InterruptedException {
        return userService.getProfileOfMostPopularUser();
    }

    @GetMapping("public/{id}/profile")
    public List<PostDTORecord> getPostsOfUser(@PathVariable Long id) {
        try {
            return postService.getPostsOfUser(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @PostMapping("user/user={userId}&subscription={id}")
    public void subscribe(@PathVariable Long userId, @PathVariable Long id) {
        userService.subscribe(userId, id);
    }

}
