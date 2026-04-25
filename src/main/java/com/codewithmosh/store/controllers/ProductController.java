package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

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
    @PostMapping
    public ResponseEntity<Void> createProduct(
            @RequestBody ProductDto request,
            UriComponentsBuilder uriBuilder){
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toEntity(request);
        product.setCategory(category);
        productRepository.save(product);
        var productDto = productMapper.toProductDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto productDto
    ){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return  ResponseEntity.notFound().build();
        }
        productMapper.update(productDto, product);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(id);
        return ResponseEntity.ok(productDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
