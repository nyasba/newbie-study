package com.newbie.testsample.repository;

import com.newbie.testsample.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByType(String type);
}
