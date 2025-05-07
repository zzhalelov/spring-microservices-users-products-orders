package org.example.springmicroservices.client;

import org.example.springmicroservices.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class ProductClient {
    private final RestTemplate restTemplate;

    public ProductClient(@Value("${products.server.url}") String url,
                         RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public ResponseEntity<Object> findById(long productId) {
        return restTemplate.getForEntity("/products/{id}", Object.class, Map.of("id", productId));

    }

    public ResponseEntity<Object> create(ProductDto productDto) {
        return restTemplate.postForEntity("/products", productDto, Object.class);
    }
}