package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    
    @Autowired
    private BooksRepository booksRepository;
    
    public List<BookEntity> getAll() {
        return booksRepository.findAll();
    }
    
    public void register(BookEntity bookEntity) {
        booksRepository.save(bookEntity);
    }
    
}
