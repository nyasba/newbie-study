package com.newbie.testsample.web;

import com.newbie.testsample.domain.BookEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * 蔵書管理のコントローラー
 */
@Controller
public class BookController {
    
    @GetMapping(value = "books")
    public String list(Model model) {
        List<BookEntity> bookList = Arrays.asList(
                new BookEntity("1", "はじめてのSpringBoot", "技術本"),
                new BookEntity("2", "ジャンプ", "マンガ")
        );
        
        model.addAttribute("books", bookList);
        return "books/list";
    }
    
}
