package com.newbie.testsample.web;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 蔵書管理のコントローラー
 */
@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @ModelAttribute
    BookRequest setupRequest() {
        // @RequestMappingの処理より先にオブジェクトを生成しておき
        // それに対して値をセットするのでRequestオブジェクトにSetterが必要
        return new BookRequest();
    }
    
    @GetMapping(value = "books")
    public String list(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/list";
    }
    
    @PostMapping(value = "books/create")
    String create(@Validated BookRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        BookEntity bookEntity = new BookEntity(
                request.getTitle(),
                request.getType()
        );
        
        bookService.register(bookEntity);
        return "redirect:/books";
    }
}
