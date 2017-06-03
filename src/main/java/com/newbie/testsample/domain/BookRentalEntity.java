package com.newbie.testsample.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 本を管理するドメイン
 */
@Entity
@Table(name = "book_rental")
public class BookRentalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @NotNull
    private BookEntity bookEntity;
    
    @Column(name = "rental_date")
    @NotNull
    private LocalDate rentalDate;
    
    @Column(name = "return_date")
    private LocalDate returnDate;
    
    @Column(name = "returned_date")
    private LocalDate returnedDate;
    
    public boolean isRented() {
        return (this.returnedDate == null);
    }
    
    public BookRentalEntity() {
    }
    
    public BookRentalEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
    
    public BookRentalEntity(BookEntity bookEntity, LocalDate returnDate) {
        this.bookEntity = bookEntity;
        this.rentalDate = LocalDate.now();
        this.returnDate = returnDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    @Override
    public String toString() {
        return "BookRentalEntity{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
