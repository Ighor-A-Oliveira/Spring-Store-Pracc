package com.ighor.shopping_cart.dto.request;

import com.ighor.shopping_cart.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCategoryRequest {
    private Long id;
    private String name;
    private List<Product> products;
}
