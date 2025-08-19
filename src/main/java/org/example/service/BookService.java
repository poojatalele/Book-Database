package org.example.service;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.*;

public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public long countBooksByAuthor(String author) {
        return repo.findByAuthor(author).size();
    }

    public Set<String> getAllAuthors() {
        Set<String> authors = new HashSet<>();
        List<Book> books = repo.findAll();

        for (Book book : books) {
            String author = book.getAuthor();
            if (author != null && !author.isBlank()) {
                authors.add(author);
            }
        }

        return authors;
    }
    public List<Book> getBooksByAuthor(String author) {
        return repo.findByAuthor(author);
    }
    public List<Book> getBooksByExactRating(double rating) {
        return repo.findByExactRating(rating);
    }
    public Map<String, Integer> findBooksAndPricesByAuthor(String author) {
        Map<String, Integer> result = new HashMap<>();
        List<Book> books = repo.findByAuthor(author);

        for (Book b : books) {
            result.put(b.getTitle(), b.getPrice());
        }

        return result;
    }

}
