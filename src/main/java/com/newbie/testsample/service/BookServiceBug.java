package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SQLインジェクションを埋め込んだService
 */
@Profile("bug")
@Service
public class BookServiceBug implements BookService {
    
    private static Logger logger = LoggerFactory.getLogger(BookServiceBug.class);
    
    @Autowired
    private BooksRepository booksRepository;
    
    @Override
    public List<BookEntity> getAll() {
        List<BookEntity> bookEntityList = booksRepository.findAll();
        bookEntityList.forEach(e -> logger.debug(e.toString()));
        return bookEntityList;
    }
    
    @Override
    public void register(BookEntity bookEntity) {
        logger.info("BUG SERVICE EXECUTED");
    }
    
    @Override
    public BookEntity findById(Integer id) {
        BookEntity bookEntity = booksRepository.findOne(id);
        logger.debug(bookEntity.toString());
        return bookEntity;
    }
    
}
