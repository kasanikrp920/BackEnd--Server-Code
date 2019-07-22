package com.example.demo.service;

import com.example.demo.entities.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {

    @Transactional
    @Modifying
    @Query(value = "update Book set available=available+1 where isbn=?1")
    int IncrementTotalCount(long isbn);

    @Transactional
    @Modifying
    @Query(value = "update Book set available=available-1 where isbn=?1")
    int DecrementTotalCount(long isbn);

    @Transactional
    @Modifying
    @Query(value = "select b from Book b where b.title like %?1%")
    List<Book>findAllByTitle(String title);

    @Transactional
    @Modifying
    @Query(value = "select book from Book book where book.authorName like %?1% ")
    List<Book>getAllByAuthorName(String authorName);

    @Transactional
    @Modifying
    @Query(value = "update Book set isDeleted=1 where isbn=?1")
     void updateDeletionOfBookStatus(long isbn);

}
