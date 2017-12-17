package com.newbie.testsample.service;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.repository.BookRentalRepository;
import com.newbie.testsample.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookRentalRepository bookRentalRepository;
    
    @Before
    public void setup() {
        bookRentalRepository.deleteAll();
        bookRepository.deleteAll();
    }
    
    @Test
    public void 本の登録と検索_1件() {
        
        // 本の登録
        BookEntity expected = new BookEntity("はじめてのSpringBoot", "技術本");
        bookService.register(expected);
        
        // 本の検索
        List<BookEntity> actual = bookService.getAll();
        assertThat(actual.size(), is(0)); //テストを落とす
        assertTrue(actual.get(0).isEqualsContents(expected));
        
    }
    
    @Test
    public void 本の登録と検索_2件() {
        
        // 本の登録
        BookEntity expected1 = new BookEntity("はじめてのSpringBoot", "技術本");
        BookEntity expected2 = new BookEntity("日経ビジネス", "ビジネス");
        bookService.register(expected1);
        bookService.register(expected2);
        
        // 本の検索
        List<BookEntity> actual = bookService.getAll();
        assertThat(actual.size(), is(2));
        actual.forEach(e ->
                assertTrue(e.isEqualsContents(expected1) || e.isEqualsContents(expected2))
        );
        
    }
    
    @Test
    public void 分類による本の絞り込み検索() {
        
        // 本の登録
        BookEntity expected1 = new BookEntity("はじめてのSpringBoot", "技術本");
        BookEntity expected2 = new BookEntity("日経ビジネス", "ビジネス");
        bookService.register(expected1);
        bookService.register(expected2);
        
        // 本の検索
        List<BookEntity> actual = bookService.getBooksByType("技術本");
        assertThat(actual.size(), is(1));
        assertTrue(actual.get(0).isEqualsContents(expected1));
        
    }
}
