package com.newbie.testsample.domain;

/**
 * 本を管理するドメイン
 */
public class BookEntity {
    
    private String id;
    private String title;
    private String type; // 別途ENUMにするべき
    
    public BookEntity(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getType() {
        return type;
    }
}
