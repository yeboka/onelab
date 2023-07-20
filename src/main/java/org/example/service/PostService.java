package org.example.service;

import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.impl.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService (PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(UserDTO user, PostDTO post) {
        postRepository.save(post);
    }

    public List<PostDTO> getAllPostsOfUser(UserDTO userDTO) {
        return postRepository.getAllPostsOfUser(userDTO);
    }

    public void createPost(UserDTO user, PostDTO post) {
        postRepository.save(post);
    }
}
