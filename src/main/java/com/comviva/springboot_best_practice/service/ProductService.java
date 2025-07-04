package com.comviva.springboot_best_practice.service;

import com.comviva.springboot_best_practice.dto.ProductRequestDto;
import com.comviva.springboot_best_practice.dto.ProductResponseDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductResponseDto createNewProduct(ProductRequestDto productRequestDto);
    List<ProductResponseDto> getProducts();
    ProductResponseDto getProductById(long id);
    Map<String,List<ProductResponseDto>>getProductsBytypes();

}
