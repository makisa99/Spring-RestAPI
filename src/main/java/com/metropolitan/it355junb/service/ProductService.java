package com.metropolitan.it355junb.service;

import com.metropolitan.it355junb.exception.ResourceNotFoundException;
import com.metropolitan.it355junb.model.Product;
import com.metropolitan.it355junb.payload.request.ProductDtoReq;
import com.metropolitan.it355junb.payload.response.ProductDtoRes;
import com.metropolitan.it355junb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDtoRes createProduct(ProductDtoReq productDtoReq) {
        Product product = mapToEntity(productDtoReq);

        Product newProduct = productRepository.save(product);

        return mapToDTO(newProduct);
    }

    public List<ProductDtoRes> getAll() {
        return productRepository.findAll().stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    public ProductDtoRes getById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", String.valueOf(id)));

        return mapToDTO(product);
    }

    public ProductDtoRes updateProduct(long id, ProductDtoReq productDtoReq) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", String.valueOf(id)));

        product.setName(productDtoReq.getName());
        product.setPrice(productDtoReq.getPrice());
        product.setQuantity(productDtoReq.getQuantity());

        Product updatedProduct = productRepository.save(product);

        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", String.valueOf(id)));

        productRepository.delete(product);
    }

    private Product mapToEntity(ProductDtoReq productDtoReq) {
        Product product = new Product();

        product.setName(productDtoReq.getName());
        product.setPrice(productDtoReq.getPrice());
        product.setQuantity(productDtoReq.getQuantity());

        return product;
    }

    private ProductDtoRes mapToDTO(Product product) {
        ProductDtoRes productDtoRes = new ProductDtoRes();

        productDtoRes.setId(product.getId());
        productDtoRes.setName(product.getName());
        productDtoRes.setPrice(product.getPrice());
        productDtoRes.setQuantity(product.getQuantity());

        return productDtoRes;
    }
}