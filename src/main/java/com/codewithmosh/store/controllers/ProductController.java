package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> findProducts(
            @RequestParam(required = false,name = "categoryId") Byte categoryId){

        List<Product> products;
        if(categoryId != null){
            products=productRepository.findByCategoryId(categoryId);
        }else {
            products=productRepository.findAllWithCategory();
        }
        return products
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(productMapper.toProductDto(product));
    }
}
