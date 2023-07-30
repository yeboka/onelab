package org.example.controller;

import org.example.model.dto.UserDTORecord;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testListUsers() throws Exception {
        List<UserDTORecord> mockUsers = Arrays.asList(
                new UserDTORecord(1L, "John", 19),
                new UserDTORecord(2L, "Jane", 21)
        );
        when(userService.list()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(userService, times(1)).list();
    }

    @Test
    public void testGetMostPopularUser() throws Exception {
        UserDTORecord mockUser =  new UserDTORecord(1L, "John", 19);
        when(userService.getMostPopularUser()).thenReturn(mockUser);

        mockMvc.perform(get("/api/v1/users/most_popular_user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John"));

        verify(userService, times(1)).getMostPopularUser();
    }

    @Test
    public void testSubscribe() throws Exception {
        Long userId = 1L;
        Long subscriptionId = 2L;

        mockMvc.perform(post("/api/v1/users/user={user_id}&subscription={id}", userId, subscriptionId))
                .andExpect(status().isOk());

        verify(userService, times(1)).subscribe(userId, subscriptionId);
    }
}
