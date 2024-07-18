package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateReqDto {
    private String title;
    private String contents;
    private String author_email;

    public Post toEntity(Author author){
        System.out.println("Post[toEntity]");
        Post post = Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .build();
        return post;
    }
}
