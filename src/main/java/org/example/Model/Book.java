package org.example.Model;

import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String name;
    private double price;
    private String AuthorName;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", AuthorName='" + AuthorName + '\'' +
                '}';
    }

    public Book() {
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