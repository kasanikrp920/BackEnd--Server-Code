package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Book {

    @Id
    @Column(length = 13)
    private long ISBN;
    @NotEmpty(message = "author name should not be empty")
    @NotNull(message = "author name should not be empty")
    private String authorName;
    @NotEmpty(message = "title name should not be empty")
    @NotNull(message = "title name should not be empty")
    private String title;
    @NotEmpty(message = "is Academic should not be empty")
    @NotNull(message = "isAcademic name should not be empty")
    private String isAcademic;
    @Min(1)
    private int totalCount;

    private int isDeleted;

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    private int available;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsAcademic() {
        return isAcademic;
    }

    public void setIsAcademic(String isAcademic) {
        this.isAcademic = isAcademic;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public Book() {
    }

    public Book(long ISBN, @NotEmpty(message = "author name should not be empty") @NotNull(message = "author name should not be empty") String authorName, @NotEmpty(message = "title name should not be empty") @NotNull(message = "title name should not be empty") String title, @NotEmpty(message = "is Academic should not be empty") @NotNull(message = "isAcademic name should not be empty") String isAcademic, @Min(1) int totalCount, int isDeleted, int available) {
        this.ISBN = ISBN;
        this.authorName = authorName;
        this.title = title;
        this.isAcademic = isAcademic;
        this.totalCount = totalCount;
        this.isDeleted = isDeleted;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", isAcademic='" + isAcademic + '\'' +
                ", totalCount=" + totalCount +
                ", isDeleted=" + isDeleted +
                ", available=" + available +
                '}';
    }
}


