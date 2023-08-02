package org.example.model.dto;

import java.util.List;

public record ProfileDTORecord (String name, List<PostDTORecord> posts) {
}
