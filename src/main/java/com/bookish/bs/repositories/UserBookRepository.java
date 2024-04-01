package com.bookish.bs.repositories;

import com.bookish.bs.entities.UserBook;
import com.bookish.bs.keys.UserBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, UserBookId> {
    List<UserBook> findUserBooksById_UserId(UUID uuid);
}
