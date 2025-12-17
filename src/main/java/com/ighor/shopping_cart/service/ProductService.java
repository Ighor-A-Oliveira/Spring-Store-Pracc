package com.ighor.shopping_cart.service;

import com.ighor.shopping_cart.dto.request.AddProductRequest;
import com.ighor.shopping_cart.dto.request.UpdateProductRequest;
import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.entity.Product;
import com.ighor.shopping_cart.exception.CategoryNotFoundException;
import com.ighor.shopping_cart.exception.ProductNotFoundException;
import com.ighor.shopping_cart.repository.CategoryRepository;
import com.ighor.shopping_cart.repository.ProductRepository;
import com.ighor.shopping_cart.service.serviceInt.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(AddProductRequest request ) {

        //Checking if the category already exists in the db
        Category category = Optional.ofNullable(
                //We access cat repo to see if we get a match
                categoryRepository.findByName(request.getCategory().getName()))
                //If not we create a new category
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        request.setCategory(category);


        //Saving to db


        //Log will be here

        //Returning new product
        return productRepository.save(createProduct(request,category));
    }


    public Product createProduct(AddProductRequest request, Category category) {
        //New product
        Product prod = new Product();

        //Setting properties
        prod.setCategory(category);
        prod.setBrand(request.getBrand());
        prod.setName(request.getName());
        prod.setDescription(request.getDescription());
        prod.setInventory(request.getInventory());
        prod.setPrice(request.getPrice());

        return productRepository.save(prod);
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
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        //Search for product
        Product product = productRepository.findById(productId)
                //If product does not exist throw exception
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        //Update product
        Product updatedProduct = updateExistingProduct(product, request);

        //Save to the db
        return productRepository.save(updatedProduct);

    }

    //This method exists in order for the update function to be more atomic and reusable
    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory_Name(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategory_NameAndBrand(category, brand);
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
