package com.ighor.shopping_cart.service.serviceInt;

import com.ighor.shopping_cart.dto.request.AddProductRequest;
import com.ighor.shopping_cart.dto.request.UpdateProductRequest;
import com.ighor.shopping_cart.entity.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest addProductRequest);

    Product getProductById(Long productId);

    void deleteProductById(Long productId);

    Product updateProduct(UpdateProductRequest request, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
