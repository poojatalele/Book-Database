package org.example.repository;

import org.example.model.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    List<Book> findByAuthor(String author);
    List<Book> findByExactRating(double rating);

    Map<String, Integer> findBooksAndPricesByAuthor(String author);
}
