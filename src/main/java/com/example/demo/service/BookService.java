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
public class BookService {

    @Autowired
    public  BookRepository bookRepository;

    @Autowired
    public BorrowerRepository borrowerRepository;

    public List<Book> getAllBooks(){

        List<Book>booksList=new ArrayList<>();
        booksList= (List<Book>) bookRepository.findAll();
        return booksList;

    }

    public Book getOneBookByISBN(long isbn){
        Book book=bookRepository.findById(isbn).orElse(null);
        if(book!=null){
            return book;
        }  else{
            throw new BookNotFoundException("book is not existed with this isbn ::::"+isbn);
        }
    }

    public List<Book>getBookByAuthorName(String authorName){

        List<Book>books=new ArrayList<>();

        books =bookRepository.getAllByAuthorName(authorName);
        if(!books.isEmpty()){
            return books;
        } else{
            throw new BookNotFoundException("book is not existed with this book title ::::"+authorName);
        }
    }
    public List<Book> getBookByTitle(String title){
        List<Book>books=new ArrayList<>();

       books =bookRepository.findAllByTitle(title);
        if(!books.isEmpty()){
            return books;
        } else{
            throw new BookNotFoundException("book is not existed with this book title ::::"+title);
        }
    }


    public Book addBook(Book book){
        Book book1=bookRepository.save(book);
        return book1;
    }

    public int deleteBook(long isbn){
        Book book=bookRepository.findById(isbn).orElse(null);
        if(book==null){
            throw new BookNotFoundException("book is not existed with this isbn ::::"+isbn);
        }else{
            bookRepository.updateDeletionOfBookStatus(isbn);
            return 1;
        }
    }




    public Book  bookReturn(String emailId){
        Borrower borrower=borrowerRepository.getBorrowerByEmail(emailId);
        if(borrower!=null){
            bookRepository.IncrementTotalCount(borrower.getBookIsbn());//updating stock after returning
            borrowerRepository.updateBorrower(borrower.getBookIsbn());//performing soft delete in borrower list
            Book book=bookRepository.findById(borrower.getBookIsbn()).orElse(null);//returning book in postman
            return book;
        }else{
            throw new BookNotFoundException("Borrower not found this email id ");
        }

    }

}
