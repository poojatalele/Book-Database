package org.example.repository.csv;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.util.CsvUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class CsvBookRepository implements BookRepository {
    private final String csvPath;
    private final List<Book> cache;

    public CsvBookRepository(String csvPath) {
        this.csvPath = csvPath;
        this.cache = load();
    }

    private List<Book> load() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath, StandardCharsets.UTF_8))) {
            String header = br.readLine();
            if (header == null) return Collections.emptyList();

            String line;
            while ((line = br.readLine()) != null) {
                List<String> cols = CsvUtils.parseLine(line);
                if (cols.size() != 7) {
                    continue;
                }
                String title = cols.get(0).trim();
                String author = cols.get(1).trim();
                double rating = Double.parseDouble(cols.get(2).trim());
                int reviews = Integer.parseInt(cols.get(3).trim());
                int price = Integer.parseInt(cols.get(4).trim());
                int year = Integer.parseInt(cols.get(5).trim());
                String genre = cols.get(6).trim();


                books.add(new Book(title, author, rating, reviews, price, year, genre));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load CSV: " + csvPath, e);
        }
        return books;
    }


    @Override
    public List<Book> findAll() {
        return Collections.unmodifiableList(cache);
    }


    @Override
    public List<Book> findByAuthor(String author) {
        String a = author == null ? "" : author.trim().toLowerCase();
        return cache.stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().equals(a))
                .collect(Collectors.toList());
    }


    @Override
    public List<Book> findByExactRating(double rating) {
        return cache.stream()
                .filter(b -> Double.compare(b.getUserRating(), rating) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> findBooksAndPricesByAuthor(String author) {
        Map<String, Integer> result = new HashMap<>();
        if (author == null) {
            return result;
        }
        String a = author.trim().toLowerCase();
        for (Book b : cache) {
            if (b.getAuthor() != null && b.getAuthor().trim().toLowerCase().equals(a)) {
                result.put(b.getTitle(), b.getPrice());
            }
        }
        return result;
    }
}