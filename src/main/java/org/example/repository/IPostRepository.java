package org.example.repository;

import org.example.dto.PostDTO;
import org.example.dto.UserDTO;

import java.util.List;

public interface IPostRepository {
    boolean save(PostDTO post);

    PostDTO findById(Long id);

    List<PostDTO> findAll();




}
