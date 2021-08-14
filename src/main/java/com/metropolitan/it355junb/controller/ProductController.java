package com.metropolitan.it355junb.controller;

import com.metropolitan.it355junb.payload.request.ProductDtoReq;
import com.metropolitan.it355junb.payload.response.ProductDtoRes;
import com.metropolitan.it355junb.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDtoRes> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<ProductDtoRes> create(@RequestBody ProductDtoReq productDtoReq) {
        return new ResponseEntity<>(productService.createProduct(productDtoReq), HttpStatus.CREATED);
    }

}