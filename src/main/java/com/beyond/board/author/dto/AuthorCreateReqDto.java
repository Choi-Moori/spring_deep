package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorCreateReqDto {
    private String name;
    private String email;
    private String password;
    private Role role;

    public Author toEntity(){
        return Author.builder()
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .role(this.role).build();
    }
}
