package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDTORecord;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTORecord> list() {
        return userService.list();
    }

        @GetMapping("/most_popular_user")
    public UserDTORecord getMostPopularUser() {
        return userService.getMostPopularUser();
    }

    @PostMapping("/user={user_id}&subscription={id}")
    public void subscribe(@PathVariable Long user_id, @PathVariable Long id) {
        userService.subscribe(user_id, id);
    }

}
