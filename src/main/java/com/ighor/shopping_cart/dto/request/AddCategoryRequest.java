package com.ighor.shopping_cart.dto.request;

import com.ighor.shopping_cart.entity.Product;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class AddCategoryRequest {
    private Long id;
    private String name;
    private List<Long> productIds;
}
