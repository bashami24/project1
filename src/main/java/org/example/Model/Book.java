package org.example.Model;

import java.util.Objects;
/*
public class Book {
    private UUID id;
    private String name;
    private double price;
    private String AuthorName;

    public Book(UUID id, String sampleBook, double v, String seller1) {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", AuthorName='" + AuthorName + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(price, book.price) == 0 && Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(AuthorName, book.AuthorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, AuthorName);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public Book(String title, String AuthorName) {
        this.id = UUID.randomUUID();
        this.name= name;
    }

}
*/

public class Book {
    private static long nextId = 1; // Static variable to keep track of the next ID
    private long id; // Change from UUID to long
    private String name;
    private double price;
    private String authorName;

    // Constructors

    public  Book() {

    }

    public Book(String name, double price, String authorName) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.authorName = authorName;
    }


    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    // hashCode, equals, and toString methods

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, authorName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                id == book.id &&
                Objects.equals(name, book.name) &&
                Objects.equals(authorName, book.authorName);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}