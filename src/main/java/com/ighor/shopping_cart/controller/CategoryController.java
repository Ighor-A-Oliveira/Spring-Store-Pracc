package com.ighor.shopping_cart.controller;

import com.ighor.shopping_cart.dto.request.UpdateCategoryRequest;
import com.ighor.shopping_cart.dto.response.ApiResponse;
import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.exception.AlreadyExistsException;
import com.ighor.shopping_cart.exception.ResourceNotFoundException;
import com.ighor.shopping_cart.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategoties(){
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Categories were found", categories));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/category/{categoryId}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId){
        try {
            Category newCat = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found", newCat));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @GetMapping("/category/{categoryName}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String categoryName){
        try {
            Category newCat = categoryService.getCategoryByName(categoryName);
            return ResponseEntity.ok(new ApiResponse("Found", newCat));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    //Need to implement CategoryDto, best practice
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name){
        try {
            Category newCat = categoryService.addCategory(name);
            return ResponseEntity.ok(new ApiResponse("Successful addition", null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @DeleteMapping("/category/{categoryId}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Deletion successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @PutMapping("/category/{categoryId}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoryRequest updateRequest){
        try {
            Category updatedCat = categoryService.updateCategory(updateRequest,categoryId);
            return ResponseEntity.ok(new ApiResponse("Deletion successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }
}
