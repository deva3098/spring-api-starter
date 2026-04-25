package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.management.modelmbean.RequiredModelMBean;
import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id",target = "categoryId")
    ProductDto toProductDto(Product product);
    Product toEntity(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto productDto, @MappingTarget Product product);
}
