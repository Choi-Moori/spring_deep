package com.beyond.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetResDto {
    private Long id;
    private String title;
    private String contents;
    private String author_email;
    private String createdTime;
    private String updatedTime;
}
