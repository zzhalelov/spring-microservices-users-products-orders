package org.example.springmicroservices.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmicroservices.client.OrderClient;
import org.example.springmicroservices.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    public final OrderClient orderClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody OrderDto orderDto) {
        return orderClient.create(orderDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> findById(@PathVariable long orderId) {
        return orderClient.findById(orderId);
    }
}