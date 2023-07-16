package org.example.service;

import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.impl.CommentRepository;
import org.example.repository.impl.PostRepository;
import org.example.repository.impl.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@AllArgsConstructor
public class MainService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public MainService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public boolean save(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> findAllUser () {
        return userRepository.findAll();
    }

    public boolean removeUserById (Long id) {
        return userRepository.removeById(id);
    }

    public void createPost (UserDTO user, PostDTO post) {

        if (user.getPosts() == null) user.setPosts(new ArrayList<>());

        user.getPosts().add(post);
        postRepository.save(post);
    }

    public List<PostDTO> getAllPostsOfUser(UserDTO userDTO) {

        return userDTO.getPosts();

    }
}
