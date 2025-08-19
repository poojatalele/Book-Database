package org.example;

import org.example.repository.BookRepository;
import org.example.repository.csv.CsvBookRepository;
import org.example.service.BookService;
import org.example.model.Book;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String csvPath = Paths.get("data.csv").toAbsolutePath().toString();
        BookRepository repo = new CsvBookRepository(csvPath);

        BookService service = new BookService(repo);

        System.out.println("\n=== Total number of books by an author ===");
        String author = "George Orwell";
        System.out.println(author + " → " + service.countBooksByAuthor(author));

        System.out.println("\n=== All authors in the dataset ===");
        service.getAllAuthors().forEach(System.out::println);

        System.out.println("\n=== Names of all the books by an author ===");
        service.getBooksByAuthor(author).forEach(b -> System.out.println(b.getTitle()));

        System.out.println("\n=== Classify with a user rating ===");
        double rating = 4.7;
        service.getBooksByExactRating(rating).forEach(b -> System.out.println(b.getTitle() + " (" + b.getUserRating() + ")"));

        System.out.println("\n=== Price of all the books by an author ===");
        service.findBooksAndPricesByAuthor(author)
                .forEach((title, price) -> System.out.println(title + " → $" + price));
    }
}