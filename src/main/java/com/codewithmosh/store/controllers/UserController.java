package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public Iterable<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user->
                        new UserDto(user.getId(),user.getName(),user.getEmail()))
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        var userDto = new UserDto(user.getId(),user.getName(),user.getEmail());
        return ResponseEntity.ok(userDto);
    }
}
