package com.ighor.shopping_cart.repository;

import com.ighor.shopping_cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

<<<<<<< HEAD
=======
    Optional<Product> findById(Long id);

>>>>>>> a38cd4f (Add image/category/product DTOs, repositories, and services)
    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByBrand(String brand);

    List<Product> findByCategory_NameAndBrand(String categoryName, String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);
}
