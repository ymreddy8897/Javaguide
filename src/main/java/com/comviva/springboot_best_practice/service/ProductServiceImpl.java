package com.comviva.springboot_best_practice.service;

import com.comviva.springboot_best_practice.dto.ProductRequestDto;
import com.comviva.springboot_best_practice.dto.ProductResponseDto;
import com.comviva.springboot_best_practice.entity.Product;
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
        Product product = ValueMapper.convertToEntity(productRequestDto);
        Product saveProduct = repository.save(product);
        productResponseDto = ValueMapper.convertToDTO(saveProduct);
        return productResponseDto;

    }

    @Override
    public List<ProductResponseDto> getProducts() {
        List<ProductResponseDto> productResponseDtos = null;
        List<Product> productList = repository.findAll();
        if (!productList.isEmpty()) {

            productResponseDtos = productList.stream().map(ValueMapper::convertToDTO).collect(Collectors.toList());
        } else {
            productResponseDtos = Collections.emptyList();
        }
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto getProductById(long id) {
        //ProductResponseDTO productResponseDTO;
        Product product = repository.findById(id).orElseThrow();
        ProductResponseDto productResponseDto = ValueMapper.convertToDTO(product);
        return productResponseDto;
    }

    @Override
    public Map<String, List<ProductResponseDto>> getProductsBytypes() {
        Map<String, List<ProductResponseDto>> productMap = repository.findAll().stream().map(ValueMapper::convertToDTO)
                .filter(productResponseDto -> productResponseDto.getProductType() != null)
                .collect(Collectors.groupingBy(ProductResponseDto::getProductType));
        return productMap;
    }
}
