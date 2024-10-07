import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBookTDDTest {

    // Null ISBN value
    @Test
    public void testNullISBNDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook(null);

        // Assertion
        assertFalse(result, "Null ISBN should not delete any book.");
    }

    // Empty ISBN value
    @Test
    public void testEmptyISBNDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook("");

        // Assertion
        assertFalse(result, "Empty ISBN should not delete any book.");
    }

    // ISBN without hyphens
    @Test
    public void testISBNWithoutHyphensDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook("9783161484100");

        // Assertion
        assertFalse(result, "ISBN without hyphens should not delete any book.");
    }

    @Test
    public void testISBNContainingLettersDeletion() {
        // Action
        boolean result = DatabaseConnection.deleteBook("123-3-YZ-123456-A");

        // Assertion
        assertFalse(result, "ISBN containing letters should not delete any book.");
    }

    @Test
    public void testValidISBNDeletion() {
        // Setup
        Book book = new Book("978-3-16-148411-0", "Book 45Test", "Author Test", "Genre 7", "2000-01-01", 10);
        DatabaseConnection.addBook(book);

        // Action
        boolean result = DatabaseConnection.deleteBook(book.getIsbn());

        // Assertion
        assertTrue(result, "Valid ISBN should successfully delete the book.");
    }

}