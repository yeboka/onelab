package org.example.model.dto;

public record CommentNotificationRecord(Long userId, Integer postId, String commentText) {
}
