package com.ighor.shopping_cart.service;

import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.repository.CategoryRepository;
import com.ighor.shopping_cart.service.serviceInt.ICategoryService;

import java.util.List;

import com.ighor.shopping_cart.dto.request.AddCategoryRequest;
import com.ighor.shopping_cart.dto.request.UpdateCategoryRequest;
import com.ighor.shopping_cart.exception.AlreadyExistsException;
import com.ighor.shopping_cart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {


    //private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository){
        //this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getCategoryById(Long id) {
        //return categoryRepository.findCategoryById(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return  Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName()+" already exists"));
    }

    @Override
    public Category updateCategory(Category category) {

        //Creating new category, it is cast as Optional so we can treat exceptions
        Category newCat = Optional.of(new Category())
                //Checking if we have this category already in the db
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                //If it is not in the db then we save it
                .map(categoryRepository::save)
                //If it is we throw exception
                .orElseThrow(() -> new AlreadyExistsException(category.getProducts() + " Already Exists")) ;


        //Log will be here

        //Returning new product
        return newCat;
    }

    private Category createCategory(AddCategoryRequest request) {
        return new Category();
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest request, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Category updatedCategory = updateExistingCategory(category, request);
        return categoryRepository.save(updatedCategory);
    }

    private Category updateExistingCategory(Category existingCategory, UpdateCategoryRequest request){
        existingCategory.setName(request.getName());
        return existingCategory;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
                (category) -> categoryRepository.delete(category),
                () -> {throw new ResourceNotFoundException("Category not found");
                }
        );
    }
}
