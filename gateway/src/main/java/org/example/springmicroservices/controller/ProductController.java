package org.example.springmicroservices.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmicroservices.client.ProductClient;
import org.example.springmicroservices.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    public final ProductClient productClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductDto productDto) {
        return productClient.create(productDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> findById(@PathVariable long productId) {
        return productClient.findById(productId);
    }
}