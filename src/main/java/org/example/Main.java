package org.example;


import org.example.config.SpringConfig;
import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    static Long postId = 0L;
    static Long commentId = 0L;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        MainService service = ctx.getBean(MainService.class);

        UserDTO user = UserDTO.builder().id(1L).name("Yerbolat Mukan").age(19).build();
        UserDTO user2 = UserDTO.builder().id(2L).name("Zhanik Manik").age(0).build();

        service.save(user);
        service.save(user2);

        service.findAllUser().forEach(System.out::println);

        service.removeUserById(2L);

        System.out.println("After removing: ");
        service.findAllUser().forEach(System.out::println);

        PostDTO post = PostDTO.builder().id(postId++).author_id(user.getId()).description("My first post").build();

        CommentDTO comment1 = CommentDTO.builder().id(commentId++)
                .authorId(user.getId())
                .postId(post.getId()).text("My first comment")
                .build();

        service.createPost(user, post);
        service.addComment(user, post, comment1);

        System.out.println("All posts of user: " + user.getName());
        service.getAllPostsOfUser(user).forEach(System.out::println);
        System.out.println("All comments of user about post");
        service.getAllCommentsOfPostOfUser(post, user).forEach(System.out::println);

    }
}
