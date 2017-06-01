package com.newbie.testsample.domain;

import javax.persistence.*;

/**
 * 本を管理するドメイン
 */
@Entity
@Table(name = "books")
public class BookEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String type; // 別途ENUMにするべき
    
    public BookEntity() {
    }
    
    public BookEntity(String title, String type) {
        this.title = title;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getType() {
        return type;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
