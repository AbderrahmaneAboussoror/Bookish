package com.bookish.bs.repositories;

import com.bookish.bs.entities.BookAd;
import com.bookish.bs.keys.BookAdId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAdRepository extends JpaRepository<BookAd, BookAdId> {
}
