
package org.example.Service;

import org.example.Model.Book;
import org.example.exception.BookAlreadyExistsException;
import org.example.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BookService {
     List<Book> bookList;
    private long nextId; // Initial ID value

    public BookService() {
        this.bookList = new ArrayList<>();
        this.nextId =1;
    }


    public List<Book> getAllBooks() {
        return bookList;
    }

    public Book addBook(Book book)  {
            if (isBookNameUnique(book.getName())) {
                book.setId(nextId++); // Assign the next available ID
                bookList.add(book);
                return book;
            } else {
                return null;
            }

    }
    public Book getBookById(long id) throws BookNotFoundException {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }

    public void updateBook(long id, Book updatedBook) throws BookNotFoundException {
        boolean found = false;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getId() == id) {
                if (isBookNameUnique(updatedBook.getName())) {
                    updatedBook.setId(id);
                    bookList.set(i, updatedBook);
                    found = true;
                    break;
                } else {
                    return;
                }
            }
        }
        if (!found) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    public void deleteBook(long id) {
         bookList.removeIf(book -> book.getId() == id);

        }


    private boolean isBookNameUnique(String name) {

        if (name == null) {
            return false;
        }
        for (Book book : bookList) {
           // System.out.println("new book name " + book.getName());
            if (book.getName().equals(name)) {
                return false;
            }
        }
       // System.out.println("Book is unique");
        return true;

    }
}
/*package org.example.Service;

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
        if (book.getId() == null && isBookNameUnique(book.getName())) {
            UUID id = UUID.randomUUID();
            book.setId(id);
            bookList.add(book);
            return book;
        } else {
            return null;
        }
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
                if (isBookNameUnique(updatedBook.getName())) {
                    updatedBook.setId(id);
                    bookList.set(i, updatedBook);
                    found = true;
                    break;
                } else {
                    return;
                }
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
    private boolean isBookNameUnique(String name) {
        for (Book book : bookList) {
            if (book.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}

*/