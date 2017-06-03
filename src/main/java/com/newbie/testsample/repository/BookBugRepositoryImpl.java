package com.newbie.testsample.repository;

import com.newbie.testsample.domain.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookBugRepositoryImpl implements BookBugRepository {
    
    private static Logger logger = LoggerFactory.getLogger(BookBugRepositoryImpl.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<BookEntity> findByTypeWithSQLInjection(String searchType) {
        String jpql = "from BookEntity where type = '" + searchType + "'";
        logger.info("SQL:" + jpql);
        return entityManager.createQuery(jpql, BookEntity.class).getResultList();
    }
}
