package com.example.demo.customExceptions;

public class DuplicateRecordInsertionException extends RuntimeException {

    public DuplicateRecordInsertionException(String message){
        super(message);
    }


}
