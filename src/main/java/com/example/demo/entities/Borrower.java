package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class Borrower {


    @Column(length = 13)
    private long bookIsbn;

    @NotNull(message = "first name should not be null")
    @NotEmpty(message = "first name should not be empty")
    private String firstName;

    @NotNull(message = "last name should not be null")
    @NotEmpty(message = "last name should not be empty")
    private String lastName;

    @Id
    @Column(length = 50)
    @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",message = "You have given Wrong email Id pattern(recommended:abc123@xyz.com)")
    private String email;

    @Min( 1)
    private int nunOfDaysLoan;

    @NotNull(message = "date should not be null")
    @NotEmpty(message = "date should not be empty")
    private String date ;
    @Column(columnDefinition = "int(5) default 0")
    private int isDeleted;

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(long bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public int getNunOfDaysLoan() {
        return nunOfDaysLoan;
    }

    public void setNunOfDaysLoan(int nunOfDaysLoan) {
        this.nunOfDaysLoan = nunOfDaysLoan;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Borrower() {
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "bookIsbn=" + bookIsbn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nunOfDaysLoan=" + nunOfDaysLoan +
                ", date='" + date + '\'' +

                '}';
    }

    @Override
    public boolean equals(Object o) {

        Borrower borrower = (Borrower) o;
        return  this.getEmail().equals(borrower.getEmail());

    }

    @Override
    public int hashCode() {
        return Objects.hash(bookIsbn, firstName, lastName, email, nunOfDaysLoan, date, isDeleted);
    }
}
