package org.example.microservices.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class UserClient {
    private final RestTemplate restTemplate;

    public UserClient(@Value("${users.server.url}") String url,
                      RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public void findById(long userId) {
        String url = "/users/" + userId;
        restTemplate.getForObject(url, String.class);
    }
}