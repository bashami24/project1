package org.example;

import io.javalin.Javalin;
import org.example.Controller.Controller;
import org.example.Service.SellerService;
import org.example.Service.BookService;

public class Main {
    public static void main(String[] args) {


        SellerService sellerService = new SellerService();
        BookService bookService = new BookService();
        Controller controller = new Controller(bookService,sellerService);

        Javalin api = controller.getAPI();
        api.start(9004);
    }
}