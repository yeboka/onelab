package org.example.service;

import org.example.model.User;
import org.example.model.dto.UserDTORecord;
import org.example.repository.IPostRepository;
import org.example.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserDTORecord> result = userService.list();

        assertEquals(2, result.size());
        assertEquals(user1.getId(), result.get(0).id());
        assertEquals(user2.getId(), result.get(1).id());

        verify(userRepository, times(1)).findAll();
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

