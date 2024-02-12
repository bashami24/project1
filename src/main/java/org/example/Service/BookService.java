package org.example.Service;

import org.example.Model.Book;
import org.example.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookService {

    private final List<Book> bookList;

    public BookService() {
        this.bookList = new ArrayList<>();
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public Book addBook(Book book) {
       UUID id = UUID.randomUUID();
       book.setId(id);
       bookList.add(book);
       return book;
    }

    public Book getBookById(UUID id) throws BookNotFoundException {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }

    public void updateBook(UUID id, Book updatedBook) throws BookNotFoundException {
        boolean found = false;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getId() == id) {
                bookList.set(i, updatedBook);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    public void deleteBook(UUID id) throws BookNotFoundException {
        boolean removed = bookList.removeIf(book -> book.getId() == id);
        if (!removed) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }
}
