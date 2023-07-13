package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.dto.UserDTO;
import org.example.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository
{
    private final List<UserDTO> users = new ArrayList<>();

    @Override
    public boolean save(UserDTO user) {
        return users.add(user);
    }

    @Override
    public UserDTO findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<UserDTO> findAll() {
        return users;
    }

    @Override
    public boolean removeById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }


//    @Override
//    public List<UserDTO> getAllPosts(UserDTO user) {
//        return null;
//    }

}
