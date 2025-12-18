package com.ighor.shopping_cart.service.serviceInt;

<<<<<<< HEAD
=======
import com.ighor.shopping_cart.dto.request.AddCategoryRequest;
import com.ighor.shopping_cart.dto.request.UpdateCategoryRequest;
>>>>>>> a38cd4f (Add image/category/product DTOs, repositories, and services)
import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.entity.Product;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

<<<<<<< HEAD
    Category updateCategory(Category category);
=======
    Category updateCategory(UpdateCategoryRequest request, Long id);
>>>>>>> a38cd4f (Add image/category/product DTOs, repositories, and services)

    void deleteCategoryById(Long id);

    //void deleteCategoryByName(String name);
}
