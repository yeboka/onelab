package org.example.service;

import org.example.dto.CommentDTO;
import org.example.dto.PostDTO;
import org.example.dto.UserDTO;
import org.example.repository.impl.CommentRepository;
import org.example.repository.impl.PostRepository;
import org.example.repository.impl.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class


MainService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public MainService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void save(UserDTO userDTO) {
        userRepository.save(userDTO);
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> findAllUser() {
        return userRepository.findAll();
    }

    public void removeUserById(Long id) {
        userRepository.removeById(id);
    }

    public void createPost(UserDTO user, PostDTO post) {

        if (user.getPosts() == null) user.setPosts(new ArrayList<>());

        user.getPosts().add(post);
        postRepository.save(post);
    }

    public List<PostDTO> getAllPostsOfUser(UserDTO userDTO) {
        return userDTO.getPosts();
    }

    public void addComment(UserDTO user, PostDTO post, CommentDTO comment) {
        commentRepository.save(comment);

        if (user.getComments() == null) user.setComments(new ArrayList<>());
        if (post.getComments() == null) post.setComments(new ArrayList<>());

        user.getComments().add(comment);
        post.getComments().add(comment);
    }

    public List<CommentDTO> getAllCommentsOfPost (PostDTO post) {
        return post.getComments();
    }

    public List<CommentDTO> getAllCommentsOfPostOfUser (PostDTO post, UserDTO user) {
        return post.getComments().stream().filter(commentDTO -> commentDTO.getAuthorId().equals(user.getId()))
                .collect(Collectors.toList());
    }
}
