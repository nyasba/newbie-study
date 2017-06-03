package com.newbie.testsample.web;

import com.newbie.testsample.domain.BookEntity;
import com.newbie.testsample.domain.BookRentalEntity;
import com.newbie.testsample.service.BookRentalService;
import com.newbie.testsample.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * レンタルのコントローラー
 */
@Controller
public class BookRentalController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRentalService bookRentalService;
    
    // submitボタンのname要素で指定した内容がparamsと一致しているものが呼び出される
    @GetMapping(value = "books/rental", params = "request")
    String rentalOrder(@RequestParam Integer id, BookRentalRequest bookRentalRequest) {
        BookEntity bookEntity = bookService.findById(id);
        BookRentalEntity bookRentalEntity = new BookRentalEntity(bookEntity);
        BeanUtils.copyProperties(bookRentalEntity, bookRentalRequest);
        return "books/rental";
    }
    
    @PostMapping(value = "books/rental")
    String rentalCreate(@RequestParam Integer id, @Validated BookRentalRequest bookRentalRequest, BindingResult result) {
        if (result.hasErrors()) {
            return rentalOrder(id, bookRentalRequest);
        }
        BookEntity bookEntity = bookService.findById(id);
        BookRentalEntity bookRentalEntity = new BookRentalEntity(bookEntity, bookRentalRequest.getReturnDate());
        bookRentalService.register(bookRentalEntity);
        
        return "redirect:/books";
    }
    
    // submitボタンのname要素で指定した内容がparamsと一致しているものが呼び出される（request=edit.htmlの戻るボタン）
    @RequestMapping(value = "books/rental", params = "goToTop")
    String goToTop() {
        return "redirect:/books";
    }
    
}
