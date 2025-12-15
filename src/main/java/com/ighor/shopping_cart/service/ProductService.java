package com.ighor.shopping_cart.service;

import com.ighor.shopping_cart.entity.Product;
import com.ighor.shopping_cart.exception.ProductNotFoundException;
import com.ighor.shopping_cart.repository.ProductRepository;
import com.ighor.shopping_cart.service.serviceInt.IProductService;

import java.util.List;

public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(
                productRepository::delete,
                () -> {throw new ProductNotFoundException("Product not found");}
        );
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
