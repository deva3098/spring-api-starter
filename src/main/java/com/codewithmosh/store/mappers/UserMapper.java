package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.regex.Pattern;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt",expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
}
