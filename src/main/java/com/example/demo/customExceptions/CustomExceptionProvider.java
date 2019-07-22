package com.example.demo.customExceptions;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class CustomExceptionProvider extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object>handleConstraintVoilation(ConstraintViolationException ex){
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        ExceptionFormat exceptionFormat=new ExceptionFormat(message.toString());
        return new ResponseEntity<>(exceptionFormat, HttpStatus.BAD_REQUEST);
    }*/

   @ExceptionHandler(BookNotFoundException.class)
   public ResponseEntity<Object>handleBookNotFoundException(BookNotFoundException ex,WebRequest req){
       ExceptionFormat exceptionFormat=new ExceptionFormat(ex.getMessage());
       return new ResponseEntity<>(exceptionFormat,HttpStatus.NOT_FOUND);
   }

    @ExceptionHandler(BorrowerNotFoundException.class)
    public ResponseEntity<Object>handleBorrowerNotFoundException(BorrowerNotFoundException ex,WebRequest req){

        ExceptionFormat exceptionFormat=new ExceptionFormat(ex.getMessage());
        return new ResponseEntity<>(exceptionFormat,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object>handleDataIntegrityViolationException( DataIntegrityViolationException ex,WebRequest req){


        ExceptionFormat exceptionFormat=new ExceptionFormat("record already exist with this email id. please try with another one");
        return new ResponseEntity<>(exceptionFormat,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         ExceptionFormat exceptionFormat=new ExceptionFormat(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(exceptionFormat, HttpStatus.BAD_REQUEST);
    }
}
