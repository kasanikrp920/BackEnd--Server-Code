package com.example.demo.customExceptions;

public class BorrowerNotFoundException extends RuntimeException {

    public BorrowerNotFoundException(String message){
        super(message);

    }
}
