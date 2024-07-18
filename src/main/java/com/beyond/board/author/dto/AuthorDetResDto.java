package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDetResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Long count;
    private String createdTime;
}
