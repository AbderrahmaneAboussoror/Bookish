package com.bookish.bs.repositories;

import com.bookish.bs.entities.Book;
import com.bookish.bs.enums.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findBookByTitle(String title);
    List<Book> findBooksByGenre(BookGenre genre);
    List<Book> findBooksByAuthor(String author);
}
