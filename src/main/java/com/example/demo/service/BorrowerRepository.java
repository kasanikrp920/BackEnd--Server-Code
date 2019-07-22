package com.example.demo.service;

import com.example.demo.entities.Borrower;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BorrowerRepository extends CrudRepository<Borrower,Long> {

    Borrower getBorrowerByEmail(String emailId);

    @Transactional
    @Modifying
    @Query(value = "update Borrower set is_deleted=1 where book_isbn=?1")
    void updateBorrower(long isbn);
}
