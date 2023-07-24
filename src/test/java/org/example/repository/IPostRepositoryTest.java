package org.example.repository;

import org.example.model.Post;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class IPostRepositoryTest {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

    @Test
    @Rollback(false)
    public void testFindPostWithMostLikes() {
        User user = User.builder().name("John Doe").build();
        userRepository.save(user);

        Post post1 = Post.builder().id(101).numOfLikes(10).description("Good post starting with 'test'").author(user).build();
        Post post2 = Post.builder().id(102).numOfLikes(12).description("Good test post").author(user).build();

        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> posts = postRepository.findPostWithMostLikes(user);
        assertEquals(1, posts.size());
        assertEquals(post1, posts.get(0));
    }

    @Test
    public void testFindPostsWithMinAmountOfLikes() {
        Post post1 = Post.builder().id(101).numOfLikes(10).description("Good post starting with 'test'").build();
        postRepository.save(post1);
        Post post2 = Post.builder().id(102).numOfLikes(1).description("Good post starting with 'test'").build();
        postRepository.save(post2);

        List<Post> posts = postRepository.findPostsWithMinAmountOfLikes();
        assertEquals(1, posts.size());
        assertEquals(post2, posts.get(0));
    }

    @Test
    public void testFindByDescriptionStartingWith() {
        Post post1 = Post.builder().id(103).numOfLikes(12).description("Post 1").build();
        postRepository.save(post1);
        Post post2 = Post.builder().id(101).numOfLikes(12).description("Post 2").build();
        postRepository.save(post2);

        List<Post> posts = postRepository.findByDescriptionStartingWith("Post");
        assertEquals(2, posts.size());
        assertEquals(post1, posts.get(0));
        assertEquals(post2, posts.get(1));
    }

    @Test
    public void testFindAllByAuthor() {
        User user = User.builder().name("John Doe").build();
        userRepository.save(user);

        Post post1 = new Post();
        post1.setDescription("Post 1");
        post1.setNumOfLikes(10);
        post1.setAuthor(user);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setDescription("Post 2");
        post2.setNumOfLikes(5);
        post2.setAuthor(user);
        postRepository.save(post2);

        List<Post> posts = postRepository.findAllByAuthor(user.getId());
        assertEquals(2, posts.size());
        assertEquals(post1, posts.get(0));
        assertEquals(post2, posts.get(1));
    }
}
