package org.example.model;

import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private final double userRating;
    private final int reviews;
    private final int price;
    private final int year;

    private final String genre;


    public Book(String title, String author, double userRating, int reviews, int price, int year, String genre) {
        this.title = title;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.year = year;
        this.genre = genre;
    }


    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getUserRating() { return userRating; }
    public int getReviews() { return reviews; }
    public int getPrice() { return price; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }


    public void printDetails() {
        System.out.printf("%s | %s | rating=%.1f | reviews=%d | $%d | %d | %s%n",
                title, author, userRating, reviews, price, year, genre);
    }
}
