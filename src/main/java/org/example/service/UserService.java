package org.example.service;

import org.example.dto.UserDTO;
import org.example.repository.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDTO userDTO) {
        userRepository.save(userDTO);
    }

    public List<UserDTO> findAllUser() {
        return userRepository.findAll();
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void removeUserById(Long id) {
        userRepository.removeById(id);
    }
}
