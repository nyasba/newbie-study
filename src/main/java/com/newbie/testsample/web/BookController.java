package com.newbie.testsample.web;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 蔵書管理のコントローラー
 */
@Controller
public class BookController {
    
    private static Logger logger = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private BookService bookService;
    
    @ModelAttribute
    BookForm setupForm() {
        // @RequestMappingの処理より先にオブジェクトを生成しておき
        // それに対して値をセットするのでFormオブジェクトにSetterが必要
        return new BookForm();
    }
    
    @GetMapping(value = "books")
    public String list(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/list";
    }
    
    @GetMapping(value = "books", params = "search")
    public String search(@RequestParam String searchType, Model model) {
        model.addAttribute("books", bookService.getBooksByType(searchType));
        return "books/list";
    }
    
    @RequestMapping(value = "books/rental", params = "searchClear")
    String searchClear() {
        return "redirect:/books";
    }
    
    
    @PostMapping(value = "books/create")
    String create(@Validated BookForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("binding result : " + result.toString());
            return list(model);
        }
        BookEntity bookEntity = new BookEntity(
                form.getTitle(),
                form.getType()
        );
        
        bookService.register(bookEntity);
        return "redirect:/books";
    }
}
