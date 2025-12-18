package com.ighor.shopping_cart.repository;

import com.ighor.shopping_cart.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
    Optional<Image> findById(Long Id);
}
