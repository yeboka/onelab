package org.example.model.dto;

public record CommentDTORecord(Integer id, Integer postId, Long authorId, String text) {}
