package com.carnicas.prova.service;

import com.carnicas.prova.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findByIdProduct(Integer id);

    List<ProductDto> findAllProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Integer productId, ProductDto productDto);

    void deleteProduct(Integer id);

}
