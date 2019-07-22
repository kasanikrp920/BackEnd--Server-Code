package com.example.demo.service;


import com.example.demo.customExceptions.BookNotFoundException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BorrowerService {
    @Autowired
    BorrowerRepository borrowerRepository;
    @Autowired
    BookRepository bookRepository;

    public Borrower addBorrowerDetails(Borrower borrower){


        Book book=bookRepository.findById(borrower.getBookIsbn()).orElse(null);
        if(book!=null){

            Borrower borrower1 = borrowerRepository.save(borrower);
            bookRepository.DecrementTotalCount(borrower.getBookIsbn());
            return borrower1;
        }  else{
            throw new BookNotFoundException("book is not existed with this isbn ::::"+ borrower.getBookIsbn());
        }
        }



    public List<Borrower>getAllBorrower(){
        List<Borrower>borrowerList=new ArrayList<>();
        borrowerList= (List<Borrower>) borrowerRepository.findAll();
        return borrowerList;
    }

    public Borrower getOneBorrower(String emailId){
        return borrowerRepository.getBorrowerByEmail(emailId);
    }
}
