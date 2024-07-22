package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostCreateReqDto {
    private String title;
    private String contents;
    private String author_email;
    private String appointment;
    private String appointmentTime;

    public Post toEntity(Author author, LocalDateTime appointmentTime){
        Post post = Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .appointment(this.appointment)
                .appointmentTime(appointmentTime)
                .build();
        return post;
    }
}
