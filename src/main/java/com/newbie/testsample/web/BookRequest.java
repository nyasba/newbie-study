package com.newbie.testsample.web;

import org.hibernate.validator.constraints.NotEmpty;

public class BookRequest {
    
    @NotEmpty
    private String title;
    
    @NotEmpty
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
        return "BookRequest{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
