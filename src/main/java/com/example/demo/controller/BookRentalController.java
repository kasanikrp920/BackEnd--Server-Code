package com.example.demo.controller;

import com.example.demo.customExceptions.BorrowerNotFoundException;
import com.example.demo.customExceptions.DuplicateRecordInsertionException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrower;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRentalController {

    @Autowired
    public BookService bookService;

    @Autowired
    public BorrowerService borrowerService;

    @GetMapping("/books")
    public List<Book> getAllResources(){

        return bookService.getAllBooks();
    }

    @GetMapping("/book/byIsbn/{isbn}")
    public Book getOneResourceByISbn(@PathVariable String isbn){
        return bookService.getOneBookByISBN(Integer.parseInt(isbn));
    }
    @GetMapping("/book/byAuthorName/{authorName}")
    public List<Book>getOneResourceByAuthorName(@PathVariable String authorName){
        List<Book>bookList=new ArrayList<>();
           bookList=bookService.getBookByAuthorName(authorName);
           return bookList;
    }
    @GetMapping("/book/byTitle/{title}")
    public List<Book> getOneResourceByTitle(@PathVariable String title){
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/books")
    public ResponseEntity<Book>createResource(@Valid  @RequestBody Book book){
        Book book1=bookService.addBook(book);
        if(book1!=null) {
            return new ResponseEntity<>(book1, HttpStatus.CREATED);
        }else{
            throw new DuplicateRecordInsertionException("the book already existed in the store");
        }

    }
    @DeleteMapping("/book/{isbn}")
    public String deleteResource(@PathVariable String isbn){
        int status=bookService.deleteBook(Long.parseLong(isbn));
        if(status==0){
            return  "deleting record failed";
        }else{
            return "record deleted successfully";
        }
    }
   //************************************************************************************

   @PostMapping("/rent")
   public ResponseEntity<String>createResource(@Valid  @RequestBody Borrower borrower){

        Borrower borrower1=borrowerService.addBorrowerDetails(borrower);

        if(borrower1!=null) {
            return new ResponseEntity<>("book successfully rented to the borrower  \n "+borrower1.toString(), HttpStatus.CREATED);
        }
        return null;
   }

   @GetMapping("/borrowers")
   public List<Borrower> getAllResourcesForBorrowers(){

       return borrowerService.getAllBorrower();
   }

   @GetMapping("/borrower/{emailId}")
    public Borrower getBorrower(@PathVariable String emailId){

        Borrower borrower=borrowerService.getOneBorrower(emailId);
        if(borrower!=null){
            return  borrower;
        }else {

            throw new BorrowerNotFoundException("Borrower Not found on this email id:: "+emailId);
        }
    }

    @PutMapping("/return/{emailId}")
    public ResponseEntity<String> returnBook(@PathVariable String emailId){
        Book book=bookService.bookReturn(emailId);
        if(book!=null){
            return new ResponseEntity<>("book returned successfully....\n"+book.toString(),HttpStatus.OK);
        }

        return null;
    }




}
