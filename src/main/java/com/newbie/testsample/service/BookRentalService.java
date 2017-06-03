package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.domain.BookRentalCheckStatus;
import com.newbie.testsample.domain.BookRentalEntity;
import com.newbie.testsample.repository.BookRentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookRentalService {
    
    private static Logger logger = LoggerFactory.getLogger(BookRentalService.class);
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRentalRepository bookRentalRepository;
    
    private LocalDate TODAY = LocalDate.now();
    
    public BookRentalCheckStatus canRental(Integer id, LocalDate returnDate) {
        BookEntity bookEntity = bookService.findById(id);
        return bookEntity.canRental(TODAY, returnDate);
    }
    
    public void rental(Integer id, LocalDate returnDate) {
        BookEntity bookEntity = bookService.findById(id);
        BookRentalEntity bookRentalEntity = bookEntity.createRental(TODAY, returnDate);
        logger.debug("rental input:" + bookRentalEntity.toString());
        bookRentalRepository.save(bookRentalEntity);
    }
//        public void rental(BookRentalEntity bookRentalEntity) {
//            logger.debug("rental input:" + bookRentalEntity.toString());
//            bookRentalRepository.save(bookRentalEntity);
//        }
    
    public void _return(Integer rentalId) {
        logger.debug("return input:" + String.valueOf(rentalId));
        BookRentalEntity bookRentalEntity = bookRentalRepository.findOne(rentalId);
        
        if (bookRentalEntity != null) {
            bookRentalEntity.setReturnedDate(LocalDate.now());
            bookRentalRepository.save(bookRentalEntity);
        } else {
            logger.info("rental entity not found");
        }
        
    }
}
