package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResDto {
    private Long id;
    private String title;
//    Author객체 그 자체를 return하게 되면 Author안에 Post가 재참조 되어, 순환참조가 발생한다.
//    private Author author;
    private String author_email;
    private String appointment;
}
