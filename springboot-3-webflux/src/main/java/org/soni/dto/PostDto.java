package org.soni.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private String id;
    private String title;
    private String description;
    private String body;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
