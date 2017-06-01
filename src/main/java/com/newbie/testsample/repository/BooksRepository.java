package com.newbie.testsample.repository;

import com.newbie.testsample.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BookEntity, String> {
}
