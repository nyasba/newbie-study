package com.newbie.testsample.web;

import com.newbie.testsample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 蔵書管理のコントローラー
 */
@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping(value = "books")
    public String list(Model model) {
    
        model.addAttribute("books", bookService.getAll());
        return "books/list";
    }
    
}
