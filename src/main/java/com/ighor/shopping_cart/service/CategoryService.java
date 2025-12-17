package com.ighor.shopping_cart.service;

import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.repository.CategoryRepository;
import com.ighor.shopping_cart.repository.ProductRepository;
import com.ighor.shopping_cart.service.serviceInt.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {


    //private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository){
        //this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
