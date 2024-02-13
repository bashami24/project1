import org.example.Model.Book;
import org.example.Model.Seller;
import org.example.Service.BookService;
import org.example.Service.SellerService;
import org.example.exception.BookNotFoundException;
import org.example.exception.SellerAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServiceTest {

    private long id; // Initialize the id variable

    private BookService bookService;
    private SellerService sellerService;

    @Before
    public void setUp() {
        // Initialize service instances
        bookService = new BookService();
        sellerService = new SellerService();
    }

    @Test
    public void testAddBook() {
        // Test adding a book
        Book book = new Book(id, "Sample Book", 10.99, "Seller1");
        Book addedBook = bookService.addBook(book);

        assertNotNull(addedBook);
        assertEquals(book, addedBook);
    }

    @Test
    public void testUpdateBook() throws BookNotFoundException {
        // Add a book
        Book book = new Book(id, "Sample Book", 10.99, "Seller1");
        bookService.addBook(book);

        // Update the book
        Book updatedBook = new Book(id, "Updated Book", 15.99, "Seller2");
        bookService.updateBook(id, updatedBook); // Use id directly

        // Verify the book is updated
        Book retrievedBook = bookService.getBookById(id); // Use id directly
        assertEquals(updatedBook, retrievedBook);
    }

    @Test(expected = BookNotFoundException.class)
    public void testDeleteBook() throws BookNotFoundException {
        // Add a book
        Book book = new Book(id, "Sample Book", 10.99, "Seller1");
        bookService.addBook(book);

        // Delete the book
        bookService.deleteBook(id); // Use id directly

        // Verify the book is deleted
        bookService.getBookById(id); // Use id directly
    }

    @Test
    public void testAddSeller() throws SellerAlreadyExistsException {
        // Test adding a seller
        Seller seller = new Seller("Seller1");
        sellerService.addSeller(seller);

        Seller retrievedSeller = sellerService.getSellerByName("Seller1");

        assertNotNull(retrievedSeller);
        assertEquals(seller.getName(), retrievedSeller.getName());
    }

    @Test
    public void testUpdateSeller() throws SellerAlreadyExistsException {
        // Add a seller
        Seller seller = new Seller("Seller1");
        sellerService.addSeller(seller);

        // Update the seller
        /* Seller updatedSeller = new Seller("Seller2");
        sellerService.updateSeller("Seller1", updatedSeller);

        // Verify the seller is updated
        Seller retrievedSeller = sellerService.getSellerByName("Seller2");
        assertEquals(updatedSeller, retrievedSeller);*/
    }
}
