package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    
    public List<BookEntity> getAll() {
        return Arrays.asList(
                new BookEntity("1", "はじめてのSpringBoot", "技術本"),
                new BookEntity("2", "ジャンプ", "マンガ")
        );
    }
    
}
