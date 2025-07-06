package com.comviva.springboot_best_practice.service;

import com.comviva.springboot_best_practice.dto.ProductRequestDto;
import com.comviva.springboot_best_practice.dto.ProductResponseDto;
import com.comviva.springboot_best_practice.entity.Product;
import com.comviva.springboot_best_practice.exception.ProductNotFoundException;
import com.comviva.springboot_best_practice.exception.ProductServiceBusinessException;
import com.comviva.springboot_best_practice.repository.ProductRepository;
import com.comviva.springboot_best_practice.util.ValueMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponseDto createNewProduct(ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto;
        try {

            Product product = ValueMapper.convertToEntity(productRequestDto);
            Product saveProduct = repository.save(product);
            productResponseDto = ValueMapper.convertToDTO(saveProduct);

        } catch (Exception e) {
            throw new ProductNotFoundException("Exception occurred while create new product!");
        }
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProducts() {

        List<ProductResponseDto> productResponseDtos = null;

        try {
            List<Product> productList = repository.findAll();
            if (!productList.isEmpty()) {

                productResponseDtos = productList.stream().map(ValueMapper::convertToDTO).collect(Collectors.toList());
            } else {
                productResponseDtos = Collections.emptyList();
            }
        } catch (Exception e) {
            throw new ProductServiceBusinessException("Exception occurred while fetch all products from Database");
        }
        return productResponseDtos;

    }

    @Override
    public ProductResponseDto getProductById(long id) {

        ProductResponseDto productResponseDto = null;
        try {
            Product product = repository.findById(id).orElseThrow();
            productResponseDto = ValueMapper.convertToDTO(product);
        } catch (Exception e) {
            throw new ProductServiceBusinessException("Exception occurred while fetch product from Database " + id);
        }

        return productResponseDto;
    }

    @Override
    public Map<String, List<ProductResponseDto>> getProductsBytypes() {
        Map<String, List<ProductResponseDto>> productMap = null;
        try {
            productMap = repository.findAll().stream().map(ValueMapper::convertToDTO)
                    .filter(productResponseDto -> productResponseDto.getProductType() != null)
                    .collect(Collectors.groupingBy(ProductResponseDto::getProductType));
        } catch (Exception e) {
            throw new ProductServiceBusinessException("Exception occurred while fetch product from Database ");
        }
        return productMap;
    }
}
