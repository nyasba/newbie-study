package com.newbie.testsample.web;

import com.newbie.testsample.domain.BookRentalCheckStatus;
import com.newbie.testsample.service.BookRentalService;
import com.newbie.testsample.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * レンタルのコントローラー
 */
@Controller
public class BookRentalController {
    
    private static Logger logger = LoggerFactory.getLogger(BookRentalController.class);
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRentalService bookRentalService;
    
    // submitボタンのname要素で指定した内容がparamsと一致しているものが呼び出される
    @GetMapping(value = "books/rental", params = "request")
    String rentalRequest(@RequestParam Integer id, @ModelAttribute BookRentalForm bookRentalForm, Model model) {
        // 初期値のセット
        bookRentalForm.setReturnDateIfNotExist(LocalDate.now());
        
        return "books/rental";
    }
    
    @PostMapping(value = "books/rental")
    String rentalCreate(@RequestParam Integer id, @Validated BookRentalForm bookRentalForm, BindingResult result, Model model) {
    
        // バリデーションチェック
        if (result.hasErrors()) {
            logger.error("binding result : " + result.toString());
            return rentalRequest(id, bookRentalForm, model);
        }
    
        // 日付などの業務的なチェック
        BookRentalCheckStatus checkStatus = bookRentalService.canRental(id, bookRentalForm.getReturnDate());
        if (checkStatus.isError()) {
            result.addError(new FieldError("bookRentalForm", "returnDate", bookRentalForm.getReturnDate(), false, null, null, checkStatus.getMessage()));
            logger.error("binding result : " + result.toString());
            return rentalRequest(id, bookRentalForm, model);
        }
    
        bookRentalService.rental(id, bookRentalForm.getReturnDate());
        
        return "redirect:/books";
    }
    
    
    @PostMapping(value = "books/return")
    String rentalRequest(@RequestParam Integer rentalId) {
        bookRentalService._return(rentalId);
        return "redirect:/books";
    }
    
    
    // submitボタンのname要素で指定した内容がparamsと一致しているものが呼び出される（request=rental.htmlの戻るボタン）
    @RequestMapping(value = "books/rental", params = "goToTop")
    String goToTop() {
        return "redirect:/books";
    }
    
}
