package com.beyond.board.author.domain;

import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
//builder어노테이션을 통해 매개변수의 순서, 매개변수 수 의 개수 등을 유연하게 세팅하여 생성자로서 활용.
@Builder
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
//    사용자가 Strin요청해도 Role클래스 자동형변환.
    @Enumerated(EnumType.STRING)
    private Role role;
    // 일반적으로 부모 엔티티(자식 객체에 영향을 끼칠 수 있는 엔티티)에 cascade 옵션을 설정
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts;

    public AuthorListResDto listFromEntity(){
        return AuthorListResDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .role(this.role)
                .build();
    }

    public AuthorDetResDto detFromEntity(){
        LocalDateTime createdTime = this.getCreateTime();
        String value = createdTime.getYear()+"년 "
                +createdTime.getMonthValue()+"월 "    // getMonth()로 하면 6월인 경우 July가 나온다.
                +createdTime.getDayOfMonth()+"일";

        return AuthorDetResDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .count((long) this.getPosts().size())
                .createdTime(value)
                .build();
    }

    public Author updateAuthor(AuthorUpdateReqDto dto){
        this.name = dto.getName();
        this.password = dto.getPassword();
        return this;
    }
}

