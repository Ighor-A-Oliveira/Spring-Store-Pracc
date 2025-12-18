package com.ighor.shopping_cart.exception;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
