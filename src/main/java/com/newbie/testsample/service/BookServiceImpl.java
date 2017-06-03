package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service
public class BookServiceImpl implements BookService {
    
    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    
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
        booksRepository.save(bookEntity);
    }
    
    @Override
    public BookEntity findById(Integer id) {
        BookEntity bookEntity = booksRepository.findOne(id);
        logger.debug(bookEntity.toString());
        return bookEntity;
    }
}
