package org.example.springmicroservices.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmicroservices.model.User;
import org.example.springmicroservices.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }
}