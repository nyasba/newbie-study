package com.newbie.testsample.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 本を管理するドメイン
 */
@Entity
@Table(name = "books")
public class BookEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String type; // 別途ENUMにするべき
    
    @OneToMany(mappedBy = "bookEntity", fetch = FetchType.EAGER)
    private List<BookRentalEntity> bookRentalEntityList;
    
    public LocalDate getLatestRentalReturnDate() {
        return getLatestRentalEntityOptional()
                .map(BookRentalEntity::getReturnDate)
                .orElse(null); // null返すの微妙。。
    }
    
    public Integer getLatestRentalId() {
        return getLatestRentalEntityOptional()
                .map(BookRentalEntity::getId)
                .orElse(null); // null返すの微妙。。
    }
    
    private Optional<BookRentalEntity> getLatestRentalEntityOptional() {
        return bookRentalEntityList.stream()
                .filter(BookRentalEntity::isRented)
                .findFirst();
    }
    
    public BookRentalCheckStatus canRental(LocalDate today, LocalDate returnDate) {
        if (this.type.equals("技術本")) {
            LocalDate rentalLimitDate = today.plusDays(14L);
            if ((returnDate.isEqual(today) || returnDate.isAfter(today))
                    && (returnDate.isEqual(rentalLimitDate) || returnDate.isBefore(rentalLimitDate))) {
                return BookRentalCheckStatus.OK;
            } else {
                return BookRentalCheckStatus.OUT_OF_RANGE_TECH;
            }
        } else {
            LocalDate rentalLimitDate = today.plusDays(7L);
            if ((returnDate.isEqual(today) || returnDate.isAfter(today))
                    && (returnDate.isBefore(rentalLimitDate))) {
                return BookRentalCheckStatus.OK;
            } else {
                return BookRentalCheckStatus.OUT_OF_RANGE_OTHERS;
            }
        }
    }
    
    public BookRentalEntity createRental(LocalDate today, LocalDate returnDate) {
        if (canRental(today, returnDate).isError()) {
            throw new IllegalStateException("rental status is not ok");
        }
        return new BookRentalEntity(this, returnDate);
    }
    
    public BookEntity() {
    }
    
    public BookEntity(String title, String type) {
        this.title = title;
        this.type = type;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getType() {
        return type;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<BookRentalEntity> getBookRentalEntityList() {
        return bookRentalEntityList;
    }
    
    public void setBookRentalEntityList(List<BookRentalEntity> bookRentalEntityList) {
        this.bookRentalEntityList = bookRentalEntityList;
    }
    
    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", bookRentalEntityLatest=" + getLatestRentalEntityOptional().map(BookRentalEntity::toString).orElse("") +
                '}';
    }
    
    public boolean isEqualsContents(BookEntity e) {
        if (e == null) {
            return false;
        }
        return this.getTitle().equals(e.getTitle()) && this.getType().equals(e.getType());
    }
}
