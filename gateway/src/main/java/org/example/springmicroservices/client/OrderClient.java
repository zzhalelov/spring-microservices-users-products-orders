package org.example.springmicroservices.client;

import org.example.springmicroservices.dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Component
public class OrderClient {
    private final RestTemplate restTemplate;

    public OrderClient(@Value("${orders.server.url}") String url,
                       RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    public ResponseEntity<Object> findById(long orderId) {
        return restTemplate.getForEntity("/orders/{id}", Object.class,
                Map.of("id", orderId));
    }

    public ResponseEntity<Object> create(OrderDto orderDto) {
        return restTemplate.postForEntity("/orders", orderDto, Object.class);
    }
}