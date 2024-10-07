import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteBookTest {

    // Test case 1: Successfully delete a book that exists
    @Test
    public void testDeleteBookSuccess() {
        // Assume this ISBN exists in the database
        String isbn = "978-1-23-456123-0";
        Book addTestbook = new Book("978-1-23-456123-0", "Test Book", "Test Author", "Test Genre", "2024-10-01", 10);
        DatabaseConnection.addBook(addTestbook);
        // Call deleteBook and assert that it returns true
        boolean result = DatabaseConnection.deleteBook(isbn);
        assertTrue(result, "The book should be deleted successfully.");
    }

    // Test case 2: Try to delete a book that does not exist
    @Test
    public void testDeleteNonExistentBook() {
        // Assume this ISBN does not exist in the database
        String isbn = "123-4-57-890002-3";

        // Call deleteBook and assert that it returns false
        boolean result = DatabaseConnection.deleteBook(isbn);
        assertFalse(result, "The method should return false when trying to delete a non-existent book.");
    }


    @Test
    public void testDeleteBookWithEmptyISBN() {
        String emptyIsbn = "";
        // Call deleteBook with an empty string and assert that it returns false
        boolean result = DatabaseConnection.deleteBook(emptyIsbn);
        assertFalse(result, "The method should return false when the ISBN is empty.");
    }
}
