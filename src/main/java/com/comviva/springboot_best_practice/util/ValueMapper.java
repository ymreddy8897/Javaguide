package com.comviva.springboot_best_practice.util;

import com.comviva.springboot_best_practice.dto.ProductRequestDto;
import com.comviva.springboot_best_practice.dto.ProductResponseDto;
import com.comviva.springboot_best_practice.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValueMapper {

    public static Product convertToEntity(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setProductType(productRequestDto.getProductType());
        product.setQuantity(productRequestDto.getQuantity());
        product.setPrice(productRequestDto.getPrice());
        product.setSupplierName(productRequestDto.getSupplierName());
        product.setSupplierCode(productRequestDto.getSupplierCode());
        return product;
    }
    public static ProductResponseDto convertToDTO(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setProductType(product.getProductType());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setSupplierName(product.getSupplierName());
        productResponseDto.setSupplierCode(product.getSupplierCode());
        return productResponseDto;
    }

    public static String jsonAsString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
