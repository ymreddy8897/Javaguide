package com.comviva.springboot_best_practice.controller;

import com.comviva.springboot_best_practice.dto.APIResponse;
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

    public static final String SUCCESS ="Success";
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> createNewProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.createNewProduct(productRequestDto);
        APIResponse<ProductResponseDto> responseDto = APIResponse.<ProductResponseDto>builder()
                .status(SUCCESS)
                .results(productResponseDto)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getProducts() {

        List<ProductResponseDto> products = productService.getProducts();
        APIResponse<List<ProductResponseDto>> responseDto = APIResponse.<List<ProductResponseDto>>builder()
                .status(SUCCESS)
                .results(products)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse>getProductById(@PathVariable long id){
        ProductResponseDto productById = productService.getProductById(id);
        APIResponse<ProductResponseDto> responseDto = APIResponse.<ProductResponseDto>builder()
                .status(SUCCESS)
                .results(productById)
                .build();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/productType")
    public ResponseEntity<APIResponse>getProductsBytypes(){
        Map<String, List<ProductResponseDto>> productsBytypes = productService.getProductsBytypes();
        APIResponse<Map<String, List<ProductResponseDto>>> responseDto = APIResponse
                .<Map<String, List<ProductResponseDto>>>builder()
                .status(SUCCESS)
                .results(productsBytypes)
                .build();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
