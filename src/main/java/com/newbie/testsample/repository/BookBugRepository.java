package com.newbie.testsample.repository;

import com.newbie.testsample.domain.BookEntity;

import java.util.List;

public interface BookBugRepository {
    List<BookEntity> findByTypeWithSQLInjection(String searchType);
}
