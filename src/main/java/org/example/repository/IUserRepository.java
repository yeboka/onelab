package org.example.repository;

import org.example.dto.UserDTO;

import java.util.List;

public interface IUserRepository {
    boolean save(UserDTO user) ;
    UserDTO findById(Long id);
    List<UserDTO> findAll();

    boolean removeById (Long id);

//    List<UserDTO> getSubscriptions(UserDTO user) ;
}
