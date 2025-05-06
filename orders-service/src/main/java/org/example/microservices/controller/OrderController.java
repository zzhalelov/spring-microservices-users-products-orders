package org.example.microservices.controller;

import org.example.microservices.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final Map<Long, Order> orders = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private long nextId;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        String userServerUrl = "http://localhost:8081";
        String userUrl = userServerUrl + "/users/" + order.getUserId();
        String productServerUrl = "http://localhost:8082";
        String productUrl = productServerUrl + "/products/" + order.getProductId();

        try {
            restTemplate.getForObject(userUrl, String.class);
            restTemplate.getForObject(productUrl, String.class);
        } catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (order.getUserId() == null || order.getProductId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "\t\n" +
                    "Пользователь или товар не найден");
        }

        order.setId(++nextId);
        order.setStatus("CREATED");
        orders.put(order.getId(), order);
        return order;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable long id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order;
    }

    @GetMapping
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}