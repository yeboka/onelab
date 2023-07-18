package org.example.repository;

import org.example.dto.UserDTO;

import java.util.List;

public interface IUserRepository {
    void save(UserDTO user);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    void removeById(Long id);

    void createTable();

    void dropTable();
}
