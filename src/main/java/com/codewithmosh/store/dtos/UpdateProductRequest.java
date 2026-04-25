package com.codewithmosh.store.dtos;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private Double price;
    private Byte categoryId;
}
