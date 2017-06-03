package com.newbie.testsample.web;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookRentalRequest {
    
    @NotNull
    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate returnDate;
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    @Override
    public String toString() {
        return "BookRentalRequest{" +
                "returnDate=" + returnDate +
                '}';
    }
}
