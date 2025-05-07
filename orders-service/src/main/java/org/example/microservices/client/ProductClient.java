package org.example.microservices.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class ProductClient {
    private final RestTemplate restTemplate;

    public ProductClient(@Value("${products.server.url}") String url,
                         RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public void findById(long productId) {
        String url = "/products/" + productId;
        restTemplate.getForObject(url, String.class);
    }
}