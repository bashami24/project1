package org.example.Controller;
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

        app.get("/books", context -> {
            List<Book> books = bookService.getAllBooks();
            context.json(books);
        });

        app.get("/books/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            try {
                Book book = bookService.getBookById(id);
                context.json(book);
            } catch (BookNotFoundException e) {
                context.status(404);
            }
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
        app.post("books", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Book newBook = om.readValue(context.body(), Book.class);

                // Check if the book name is non-null
              if (newBook.getName() == null || newBook.getName().isEmpty()) {
                context.status(400).result("Book name should not be empty");
              return;
              }

                 //Check if the price is over 0
               if (newBook.getPrice() <= 0) {
               context.status(400).result("Price should be over 0");
               return;
               }

                // Check if the seller name refers to an existing seller
              Seller existingSeller = sellerService.getSellerByName(newBook.getAuthorName());
               if (existingSeller == null) {
                   context.status(400).result("Seller Name should refer to an existing Seller");
                  return;
              }
                // Add the book if all criteria are met
                bookService.addBook(newBook);
                context.status(201);
            } catch (JsonProcessingException e) {
                context.status(400).result("Invalid book data");
            }
        });

        app.put("/books/{id}", ctx -> {
            long id = Long.parseLong(ctx.pathParam("id"));
            try {
                ObjectMapper om = new ObjectMapper();
                Book updatedBook = om.readValue(ctx.body(), Book.class);

                // Check if seller name refers to an existing seller
                if (sellerService.getSellerByName(updatedBook.getAuthorName()) == null) {
                    ctx.status(400).result("Seller name should refer to an existing seller");
                    return;
                }

                // Check if price is greater than 0
                if (updatedBook.getPrice() <= 0) {
                    ctx.status(400).result("Price should be greater than 0");
                    return;
                }

                // Update the book
                updatedBook.setId(id);
                bookService.updateBook(id, updatedBook);
                ctx.status(200);
            } catch (JsonProcessingException | BookNotFoundException e) {
                ctx.status(400).result("Invalid book data");
            }
        });

        app.delete("/books/{id}", ctx -> {
            long id = Long.parseLong(ctx.pathParam("id"));
            bookService.deleteBook(id);
            ctx.status(200);
        });

        // Endpoints for sellers
        app.get("/sellers", ctx -> {
            List<Seller> sellers = sellerService.getAllSellers();
            ctx.json(sellers);
        });



        return app;
    }
}