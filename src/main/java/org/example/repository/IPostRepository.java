package org.example.repository;

import org.example.dto.PostDTO;
import org.example.dto.UserDTO;

import java.util.List;

public interface IPostRepository {
    void save(PostDTO post);

    PostDTO findById(Long id);

    List<PostDTO> findAll();

    void removeById (Long id);

    List<PostDTO> getAllPostsOfUser(UserDTO userDTO);

    void createTable();

    void dropTable();
}
