package com.example.demo.customExceptions;

import org.springframework.stereotype.Component;

@Component
public class ExceptionFormat {
    private String message;


    public ExceptionFormat() {
    }


    public ExceptionFormat(String message) {
        this.message = message;

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionFormat{" +
                "message='" + message + '\'' +

                '}';
    }
}
