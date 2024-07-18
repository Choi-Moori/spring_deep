package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public PostListResDto listFromEntity(){
        return PostListResDto.builder()
                .id(this.id)
                .title(this.title)
//                .author(this.author)
                .author_email(this.author.getEmail())
                .build();
    }

    public PostDetResDto detFromEntity(){
        LocalDateTime createdTime = this.getCreateTime();
        String value = createdTime.getYear()+"년 "
                +createdTime.getMonthValue()+"월 "    // getMonth()로 하면 6월인 경우 July가 나온다.
                +createdTime.getDayOfMonth()+"일";
        LocalDateTime updatedTime = this.getUpdatedTime();
        String value1 = updatedTime.getYear()+"년 "
                +updatedTime.getMonthValue()+"월 "    // getMonth()로 하면 6월인 경우 July가 나온다.
                +updatedTime.getDayOfMonth()+"일";

        return PostDetResDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .createdTime(value)
                .updatedTime(value1)
                .build();
    }
}
