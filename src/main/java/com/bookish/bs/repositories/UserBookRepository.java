package com.bookish.bs.repositories;

import com.bookish.bs.entities.UserBook;
import com.bookish.bs.keys.UserBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, UserBookId> {
}
