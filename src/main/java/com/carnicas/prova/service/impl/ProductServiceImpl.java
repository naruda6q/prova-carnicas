package com.carnicas.prova.service.impl;

import com.carnicas.prova.dto.ProductDto;
import com.carnicas.prova.mapper.ProductMapper;
import com.carnicas.prova.repository.ProductRepository;
import com.carnicas.prova.entity.Product;
import com.carnicas.prova.exception.ResourceNotFoundException;
import com.carnicas.prova.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto){

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product is not exists")
        );

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product updateProduct = productRepository.save(product);

        return ProductMapper.mapToProductDto(updateProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDto findByIdProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product is not exists")
                );
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is not exists")
        );
        productRepository.deleteById(id);
    }

}
