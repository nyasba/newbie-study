package com.newbie.testsample.domain;

import org.junit.Test;

import java.time.LocalDate;

import static com.newbie.testsample.domain.BookRentalCheckStatus.OK;
import static com.newbie.testsample.domain.BookRentalCheckStatus.OUT_OF_RANGE_TECH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookEntityTest {
    
    private static LocalDate TODAY = LocalDate.of(2017, 6, 1);
    
    @Test
    public void 技術本をレンタル可能() {
        BookEntity bookEntity = new BookEntity("dummmy", "技術本");
        BookRentalCheckStatus actual = bookEntity.canRental(TODAY, LocalDate.of(2017, 6, 3));
        assertThat(actual, is(OK));
    }
    
    @Test
    public void 技術本は2週間超えるとレンタル不可() {
        BookEntity bookEntity = new BookEntity("dummmy", "技術本");
        BookRentalCheckStatus actual = bookEntity.canRental(TODAY, LocalDate.of(2017, 6, 16));
        assertThat(actual, is(OUT_OF_RANGE_TECH));
    }
    
}
