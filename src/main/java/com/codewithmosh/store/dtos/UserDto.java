package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long getId;
    private String name;
    private String email;
}
