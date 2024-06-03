package com.carnicas.prova.controller;

import com.carnicas.prova.dto.ProductDto;
import com.carnicas.prova.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    //createProduct
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        if((productDto.getName() == null) || (productDto.getName().isBlank())){
            return ResponseEntity.badRequest().build();
        }
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
    }

    //findByIdProduct
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findByIdProduct(@PathVariable("id") Integer productId){
        ProductDto productDto = productService.findByIdProduct(productId);
        return ResponseEntity.ok(productDto);
    }

    //findAll
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAllProducts(){
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    //updateProduct
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Integer productId, @RequestBody ProductDto updatedProductDto){
        ProductDto foundProduct = productService.findByIdProduct(productId);
        if(foundProduct.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        ProductDto productDto = productService.updateProduct(productId,updatedProductDto);
        return ResponseEntity.ok(productDto);
    }

    //deleteProduct
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer productId){
        ProductDto productDto = productService.findByIdProduct(productId);
        if(productDto.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted succesfully");
    }

}
