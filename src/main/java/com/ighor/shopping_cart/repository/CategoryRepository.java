package com.ighor.shopping_cart.repository;

import com.ighor.shopping_cart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
<<<<<<< HEAD
=======

    Boolean existsByName(String name);

>>>>>>> a38cd4f (Add image/category/product DTOs, repositories, and services)
}
