package org.example.microservices.controller;

import lombok.RequiredArgsConstructor;
import org.example.microservices.client.ProductClient;
import org.example.microservices.client.UserClient;
import org.example.microservices.model.Order;
import org.example.microservices.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        userClient.findById(order.getUserId());
        productClient.findById(order.getProductId());
        order.setStatus("CREATED");
        orderRepository.save(order);
        return order;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}