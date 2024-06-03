package com.carnicas.prova.mapper;

import com.carnicas.prova.dto.ProductDto;
import com.carnicas.prova.entity.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice()
        );
    }
}
