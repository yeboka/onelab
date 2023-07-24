package org.example.service;

import org.example.model.Post;
import org.example.model.User;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    IUserRepository userRepository;
    @Mock
    IPostRepository postRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.list();

        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userRepository, times(1)).findAll();
    }

    void testGetPopularPostsOfUser() {
        User user = new User();
        user.setId(1L);

        Post post1 = Post.builder().id(101).numOfLikes(10).author(user).build();
        Post post2 = Post.builder().id(102).numOfLikes(12).author(user).build();

        when(postRepository.findPostWithMostLikes(user)).thenReturn(List.of(post1, post2));

        List<Post> result = userService.getPopularPostsOfUser(user);

        assertEquals(2, result.size());
        assertEquals(post1, result.get(0));
        assertEquals(post2, result.get(1));
    }

    @Test
    void testRemoveFirstPostWithMinAmountOfLikes() {
        Post post1 = Post.builder().id(100).numOfLikes(10).build();
        Post post2 = Post.builder().id(101).numOfLikes(9).build();

        when(postRepository.findPostsWithMinAmountOfLikes()).thenReturn(List.of(post2));

        userService.removeFirstPostWithMinAmountOfLikes();

        verify(postRepository, times(1)).findPostsWithMinAmountOfLikes();
        verify(postRepository, times(1)).delete(post2);
    }
    @Test
    void testSubscribe_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.subscribe(1L, 2L));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testSubscribe_SubscriberNotFound() {
        User user = User.builder().id(1L).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.subscribe(1L, 2L));

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
    }
}

