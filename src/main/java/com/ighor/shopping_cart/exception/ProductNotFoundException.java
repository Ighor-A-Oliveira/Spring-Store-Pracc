package com.ighor.shopping_cart.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ProductNotFoundException extends RuntimeException {


    public ProductNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
