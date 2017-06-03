package com.newbie.testsample.domain;

public enum BookRentalCheckStatus {
    OK(""),
    OUT_OF_RANGE_TECH("返却予定日を2週間以内にしてください"),
    OUT_OF_RANGE_OTHERS("返却予定日を1週間以内にしてください");
    
    private String message;
    
    BookRentalCheckStatus(String message) {
        this.message = message;
    }
    
    public boolean isError() {
        return this != OK;
    }
    
    public String getMessage() {
        return message;
    }
}
