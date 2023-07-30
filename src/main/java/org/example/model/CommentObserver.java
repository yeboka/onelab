package org.example.model;

public interface CommentObserver {
    void update(Post post, String commentText);
}
