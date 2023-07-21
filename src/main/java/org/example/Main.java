package org.example;


import org.example.config.SpringConfig;
import org.example.dto.UserDTO;
import org.example.service.CommentService;
import org.example.service.PostService;
import org.example.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = ctx.getBean(UserService.class);
        PostService postService = ctx.getBean(PostService.class);
        CommentService commentService = ctx.getBean(CommentService.class);

        userService.findAllUser();
        UserDTO user = userService.findUserById(1L);
        userService.removeUserById(user.getId());
        userService.findAllUser();
    }
}

