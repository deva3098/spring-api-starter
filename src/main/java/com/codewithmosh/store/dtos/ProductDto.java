package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class ProductDto {
    Long id;
    String name;
    String description;
    Double price;
    Byte categoryId;
}
