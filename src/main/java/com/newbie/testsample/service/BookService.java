package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;

import java.util.List;

public interface BookService {
    
    List<BookEntity> getAll();
    
    List<BookEntity> getBooksByType(String type);
    
    void register(BookEntity bookEntity);
    
    BookEntity findById(Integer id);
}
