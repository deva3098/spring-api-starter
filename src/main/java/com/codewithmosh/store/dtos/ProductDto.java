package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    Double price;
    Byte categoryId;
}
