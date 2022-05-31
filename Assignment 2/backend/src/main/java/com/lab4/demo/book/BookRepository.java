package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByNameContainingAndAuthorContainingAndGenreContainingIgnoreCase(String title, String author, String genre);
}
