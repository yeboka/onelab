package org.example.model.dto;

public record CommentDTORecord(Long id, Long postId, Long authorId, String text) {}
