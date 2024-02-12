package org.example.Controller;/*package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.example.exception.SellerNameNotUniqueException;
import org.example.exception.BookNotFoundException;
import org.example.Model.Book;
import org.example.Service.BookService;

import java.util.List;
import java.util.UUID;

public class Controller {

    SellerService authorService;
    org.example.Service.BookService bookService;

    public Controller(SellerService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public Javalin getAPI() {
        Javalin api = Javalin.create();

        api.get("health", context -> {
            context.result("Server is UP");
        });

        // CRUD operations for Authors
        api.get("authors", context -> {
            List<Seller> authorList = authorService.getAllAuthors();
            context.json(authorList);
        });

        api.post("authors", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Seller author = om.readValue(context.body(), Seller.class);
                authorService.addAuthor(author);
                context.status(201);
           }catch (JsonProcessingException e) {
              context.status(400);
          }
        });

        api.put("authors/{name}", context -> {
            String name = context.pathParam("name");
            try {
                ObjectMapper om = new ObjectMapper();
                Seller updatedAuthor = om.readValue(context.body(), Seller.class);
                authorService.updateAuthor(name, updatedAuthor);
                context.status(200);
            } catch (JsonProcessingException | SellerNameNotUniqueException e) {
                context.status(400);
            }
        });

        api.delete("authors/{name}", context -> {
            String name = context.pathParam("name");
            try {
                authorService.deleteAuthor(name);
                context.status(200);
            } catch (SellerNameNotUniqueException e) {
                context.status(404);
            }
        });

        // CRUD operations for Books
        api.get("books", context -> {
            UUID id = UUID.fromString(context.pathParam("id"));
            Book book = bookService.getBookById(id);
            if(book != null) {
                context.json(book);
                context.status(200);
            } else {context.status(404);}
        });

        api.post("books", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Book book = om.readValue(context.body(), Book.class);
                bookService.addBook(book);
                context.status(201);
            } catch (JsonProcessingException e) {
                context.status(400);
            }
        });

        api.put("books/{id}", context -> {
            UUID id = UUID.fromString(context.pathParam("id"));
            try {
                ObjectMapper om = new ObjectMapper();
                Book updatedBook = om.readValue(context.body(), Book.class);
                bookService.updateBook(id, updatedBook);
                context.status(200);
            } catch (JsonProcessingException | BookNotFoundException e) {
                context.status(400);
            }
        });

        api.delete("books/{id}", context -> {
            UUID id =UUID.fromString(context.pathParam("id"));
            try {
                bookService.deleteBook(id);
                context.status(200);
            } catch (BookNotFoundException e) {
                context.status(404);
            }
        });

        return api;
    }
}

 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.exception.BookNotFoundException;
import org.example.exception.SellerAlreadyExistsException;
import org.example.Model.Book;
import org.example.Model.Seller;
import org.example.Service.BookService;
import org.example.Service.SellerService;

import java.util.List;
import java.util.UUID;

public class Controller {

    private final BookService bookService;
    private final SellerService sellerService;

    public Controller(BookService bookService, SellerService sellerService) {
        this.bookService = bookService;
        this.sellerService = sellerService;
    }

    public Javalin getAPI(){
        Javalin app = Javalin.create();

        app.get("health", context -> {context.result("Server is UP");});

        app.get("/books", ctx -> {
            List<Book> books = bookService.getAllBooks();
            ctx.json(books);
        });

        app.get("/books/{id}", ctx -> {
            UUID id = UUID.fromString(ctx.pathParam("id"));
            try {
                Book book = bookService.getBookById(id);
                ctx.json(book);
            } catch (BookNotFoundException e) {
                ctx.status(404);
            }
        });

        app.post("/books/{id}", ctx -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Book newBook = om.readValue(ctx.body(), Book.class);
                bookService.addBook(newBook);
                ctx.status(201);
            } catch (JsonProcessingException e) {
                ctx.status(400).result("Invalid book data");
            }
        });

        app.put("/books/{id}", ctx -> {
            UUID id = UUID.fromString(ctx.pathParam("id"));
            try {
                ObjectMapper om = new ObjectMapper();
                Book updatedBook = om.readValue(ctx.body(), Book.class);
                updatedBook.setId(id);
                bookService.updateBook(id,updatedBook);
                ctx.status(200);
            } catch (JsonProcessingException | BookNotFoundException e) {
                ctx.status(400).result("Invalid book data");
            }
        });

        app.delete("/books/{id}", ctx -> {
            UUID id = UUID.fromString(ctx.pathParam("id"));
            bookService.deleteBook(id);
            ctx.status(200);
        });

        // Endpoints for sellers
        app.get("/sellers", ctx -> {
            List<Seller> sellers = sellerService.getAllSellers();
            ctx.json(sellers);
        });

        app.post("/sellers", ctx -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Seller newSeller = om.readValue(ctx.body(), Seller.class);
                sellerService.addSeller(newSeller);
                ctx.status(201);
            } catch (JsonProcessingException | SellerAlreadyExistsException e) {
                ctx.status(400).result("Invalid seller data");
            }
        });

        return app;
    }
}