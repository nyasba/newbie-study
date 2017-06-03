package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.repository.BookBugRepository;
import com.newbie.testsample.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SQLインジェクションを埋め込んだService
 */
@Profile("bug")
@Service
@Transactional
public class BookServiceBug implements BookService {
    
    private static Logger logger = LoggerFactory.getLogger(BookServiceBug.class);
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookBugRepository bookBugRepository;
    
    @Override
    public List<BookEntity> getAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        bookEntityList.forEach(e -> logger.debug(e.toString()));
        return bookEntityList;
    }
    
    @Override
    public List<BookEntity> getBooksByType(String type) {
        logger.info("BUG SERVICE EXECUTED");
        List<BookEntity> bookEntityList = bookBugRepository.findByTypeWithSQLInjection(type);
        bookEntityList.forEach(e -> logger.debug(e.toString()));
        return bookEntityList;
    }
    
    @Override
    public void register(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }
    
    @Override
    public BookEntity findById(Integer id) {
        BookEntity bookEntity = bookRepository.findOne(id);
        logger.debug(bookEntity.toString());
        return bookEntity;
    }
    
}
