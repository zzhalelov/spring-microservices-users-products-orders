package org.example.springmicroservices.client;

import org.example.springmicroservices.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class UserClient {
    private final RestTemplate restTemplate;

    public UserClient(@Value("${users.server.url}") String url,
                      RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public ResponseEntity<Object> findById(long userId) {
        return restTemplate.getForEntity("/users/{id}", Object.class,
                Map.of("id", userId));
    }

    public ResponseEntity<Object> create(UserDto userDto) {
        return restTemplate.postForEntity("/users", userDto, Object.class);
    }
}