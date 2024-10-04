import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteBookGUITest {

    // Valid ISBN deletion
    @Test
    public void testValidISBNDeletion() {
        // Setup
        Book book = new Book("978-3-16-148410-0", "Book Test", "Author Test", "Genre Test", "2020-01-01", 10);
        DatabaseConnection.addBook(book);

        // Action
        boolean result = DatabaseConnection.deleteBook(book.getIsbn());

        // Assertion
        assertTrue(result, "Valid ISBN should successfully delete the book.");
    }

    // Nonexistent ISBN
    @Test
    public void testNonExistentISBN() {
        // Action
        boolean result = DatabaseConnection.deleteBook("000-0-00-000000-0");

        // Assertion
        assertFalse(result, "Nonexistent ISBN should not delete any book.");
    }

    // Empty ISBN field
    @Test
    public void testEmptyISBNDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook("");

        // Assertion
        assertFalse(result, "Empty ISBN should not delete any book.");
    }

    // Invalid ISBN format
    @Test
    public void testInvalidISBNFormatDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook("123-XYZ");

        // Assertion
        assertFalse(result, "Invalid ISBN format should not delete any book.");
    }


    //ISBN without hyphen
    @Test
    public void testISBNWithoutHyphen() {

        boolean result = DatabaseConnection.deleteBook("9783161484100");

        // Assertion
        assertFalse(result, "Invalid ISBN format should not delete any book.");
    }

    // ISBN with whitespace (e.g., user input with leading/trailing spaces)
    @Test
    public void testISBNWithWhitespace() {

        boolean result = DatabaseConnection.deleteBook("978-3-16-14 8 4 10-0");

        // Assertion
        assertFalse(result, "Invalid ISBN format should not delete any book.");

    }

    //Deleting a book when the stock is zero (Edge case for possible library systems)
    @Test
    public void testDeleteBookWhenStockIsZero() {
        // Setup
        Book book = new Book("978-3-16-123456-0", "Test 123Book", "Test Author", "Test Genre", "2020-01-01", 0); // Stock is zero
        DatabaseConnection.addBook(book);

        // Action
        boolean result = DatabaseConnection.deleteBook(book.getIsbn());

        // Assertion
        assertTrue(result, "Book with zero stock should still be deletable.");
    }
}
