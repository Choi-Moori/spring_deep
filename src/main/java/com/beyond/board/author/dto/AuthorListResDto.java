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
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
