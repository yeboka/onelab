package org.example.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Integer id;
    private String description;
    private int numOfLikes;
}
