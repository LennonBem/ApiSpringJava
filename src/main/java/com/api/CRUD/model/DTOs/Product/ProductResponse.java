package com.api.CRUD.model.DTOs.Product;

import com.api.CRUD.model.DTOs.Category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Float price;
    private CategoryResponse category;

}
