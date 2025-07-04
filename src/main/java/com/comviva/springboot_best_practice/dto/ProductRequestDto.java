package com.comviva.springboot_best_practice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductRequestDto {

    @NotBlank(message = "product name shouldn't be null or empty")
    private String name;
    private String description;
    @NotBlank(message = "productType shouldn't be null or empty")
    private String productType;
    @Min(value = 1,message = "quantity not defined")
    private int quantity;
    @Min(value = 200, message = "product price can't be less than 200")
    @Max(value = 500000, message = "product price can't be more than 5000")
    private double price;
    @NotBlank(message = "supplierName shouldn't be null or empty")
    private String supplierName;
    private String supplierCode;


}
