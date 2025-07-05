package com.comviva.springboot_best_practice.controller;

import com.comviva.springboot_best_practice.dto.ProductRequestDto;
import com.comviva.springboot_best_practice.dto.ProductResponseDto;
import com.comviva.springboot_best_practice.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        ProductResponseDto newProduct = productService.createNewProduct(productRequestDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto>getProductById(@PathVariable long id){
        ProductResponseDto productById = productService.getProductById(id);
        return new ResponseEntity<>(productById,HttpStatus.OK);
    }
    @GetMapping("/productType")
    public ResponseEntity<Map<String,List<ProductResponseDto>>>getProductsBytypes(){
        Map<String, List<ProductResponseDto>> productsBytypes = productService.getProductsBytypes();
        return new ResponseEntity<>(productsBytypes,HttpStatus.OK);
    }

}
