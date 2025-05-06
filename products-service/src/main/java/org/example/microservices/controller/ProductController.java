package org.example.microservices.controller;

import org.example.microservices.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        product.setId(++nextId);
        products.put(product.getId(), product);
        return product;
    }

    @GetMapping
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return product;
    }
}