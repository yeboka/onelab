package org.example.controller;

import org.example.model.dto.PostDTO;
import org.example.service.PostService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerIntegrationTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testGetPostFeed() throws Exception {
        // Arrange
        Long userId = 1L;
        List<PostDTO> mockPosts = Arrays.asList(
                PostDTO.builder().id(1).description("LALA").numOfLikes(12).build(),
                PostDTO.builder().id(2).description("OKIOKI").numOfLikes(10).build()
        );
        when(postService.getPostsFeed(userId)).thenReturn(mockPosts);

        // Act & Assert
        mockMvc.perform(get("/api/v1/posts/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("LALA"))
                .andExpect(jsonPath("$[0].numOfLikes").value(12))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].description").value("OKIOKI"))
                .andExpect(jsonPath("$[1].numOfLikes").value(10));

        verify(postService, times(1)).getPostsFeed(userId);
    }

    @Test
    public void testGetPostsStartsWith() throws Exception {
        // Arrange
        String text = "test";
        List<PostDTO> mockPosts = Arrays.asList(
                PostDTO.builder().id(1).description("LALA").numOfLikes(12).build(),
                PostDTO.builder().id(2).description("OKIOKI").numOfLikes(10).build()
        );
        when(postService.searchPostsStartsWith(text)).thenReturn(mockPosts);

        // Act & Assert
        mockMvc.perform(get("/api/v1/posts/start={text}", text))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("LALA"))
                .andExpect(jsonPath("$[0].numOfLikes").value(12))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].description").value("OKIOKI"))
                .andExpect(jsonPath("$[1].numOfLikes").value(10));

        verify(postService, times(1)).searchPostsStartsWith(text);
    }

    @Test
    public void testRemoveLoser() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/v1/posts/remove_loser"))
                .andExpect(status().isOk());

        verify(postService, times(1)).removeFirstPostWithMinAmountOfLikes();
    }
}
