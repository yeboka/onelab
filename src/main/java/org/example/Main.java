package org.example;


import org.example.model.Post;
import org.example.model.User;
import org.example.service.JmsService;
import org.example.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        UserService userService = ctx.getBean(UserService.class);

//        userService.list().forEach(System.out::println);
        User user = userService.getMostPopularUser();
        List<Post> popularPosts = userService.getPopularPostsOfUser(user);
//        System.out.println(popularPosts.get(0).toString());
        List<Post> postsOfUserSubscriptions = userService.getAllPostOfSubscribersOfUser(user.getId());
//        postsOfUserSubscriptions.forEach(System.out::println);
        userService.removeFirstPostWithMinAmountOfLikes();

        userService.subscribe(1L, 5L);

        userService.searchPostsStartsWith("C");
        System.out.println(user);
        JmsService jmsService = ctx.getBean(JmsService.class);
        jmsService.sendUser("test",user.getName());

    }
}

