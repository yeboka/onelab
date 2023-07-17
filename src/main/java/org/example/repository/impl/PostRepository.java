package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.dto.PostDTO;
import org.example.repository.IPostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class PostRepository implements IPostRepository {

    private final List<PostDTO> posts = new ArrayList<>();
    @Override
    public void save(PostDTO post) {
        posts.add(post);
    }

    @Override
    public PostDTO findById(Long id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<PostDTO> findAll() {
        return null;
    }

    @Override
    public void removeById(Long id) {
        posts.removeIf(post -> post.getId().equals(id));
    }


}
