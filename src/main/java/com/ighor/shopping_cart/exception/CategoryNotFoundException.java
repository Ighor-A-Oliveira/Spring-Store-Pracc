package com.ighor.shopping_cart.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
