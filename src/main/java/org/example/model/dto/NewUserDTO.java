package org.example.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserDTO {
    private String name;
    private Integer age;
}
