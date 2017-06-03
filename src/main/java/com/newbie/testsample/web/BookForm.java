package com.newbie.testsample.web;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class BookForm {
    
    @NotEmpty(message = "タイトルを入力してください")
    private String title;
    
    @NotEmpty(message = "分類を入力してください")
    @Size(max = 10, message = "分類は10文字までにしてください")
    private String type;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "BookForm{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
