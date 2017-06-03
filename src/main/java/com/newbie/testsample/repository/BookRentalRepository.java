package com.newbie.testsample.repository;

import com.newbie.testsample.domain.BookRentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentalRepository extends JpaRepository<BookRentalEntity, Integer> {
}
