package org.example.service;

import org.example.model.Post;
import org.example.model.User;
import org.example.model.dto.PostDTO;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PostServiceTest {
    @Mock
    IUserRepository userRepository;
    @Mock
    IPostRepository postRepository;
    @InjectMocks
    PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPostsFeedWithValidUserId() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        User subscription1 = new User();
        subscription1.setId(101L);
        user.getSubscriptions().add(subscription1);

        Post post1 = Post.builder().id(101).numOfLikes(9).description("LALA").build();
        Post post2 = Post.builder().id(102).numOfLikes(9).description("OKIOKI").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findAllByAuthor(subscription1)).thenReturn(List.of(post1, post2));

        List<PostDTO> result = postService.getPostsFeed(userId);

        assertEquals(2, result.size());
    }

    @Test
    public void testSearchPostsStartsWith() {
        String searchText = "test";

        Post post1 = Post.builder().id(101).numOfLikes(9).description("Test 1").build();
        Post post2 = Post.builder().id(102).numOfLikes(9).description("Test 2").build();

        when(postRepository.findByDescriptionStartingWith(searchText)).thenReturn(List.of(post1, post2));

        List<PostDTO> result = postService.searchPostsStartsWith(searchText);

        assertEquals(2, result.size());
    }

    @Test
    void testRemoveFirstPostWithMinAmountOfLikes() {
        Post post = Post.builder().id(101).numOfLikes(9).build();

        when(postRepository.findPostsWithMinAmountOfLikes()).thenReturn(List.of(post));

        postService.removeFirstPostWithMinAmountOfLikes();

        verify(postRepository, times(1)).findPostsWithMinAmountOfLikes();
        verify(postRepository, times(1)).delete(post);
    }
}
