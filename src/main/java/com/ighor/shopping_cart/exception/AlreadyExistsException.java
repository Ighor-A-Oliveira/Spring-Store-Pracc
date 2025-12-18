package com.ighor.shopping_cart.exception;

public class AlreadyExistsException extends RuntimeException {


    public AlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
