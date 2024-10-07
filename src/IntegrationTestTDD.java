import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTestTDD {


    // Test case 1: Add, update, and delete a book
    @Test
    public void testAddUpdateDeleteBook() {
        // Step 1: Add a book
        BookTDD book = new BookTDD("978-3-16-148410-3", "Testing", "Test Author", "Test Genre", "2024-01-01", 5);
        DatabaseConnectionTDD.addBook(book);

        // Verify the book is added
        List<BookTDD> books = DatabaseConnectionTDD.searchBooks("Testing");
        assertEquals(1, books.size(), "Book should be added.");
        assertEquals("Testing", books.get(0).getTitle());

        // Step 2: Update the book
        book = new BookTDD("978-3-16-148410-3", "Updated", "Updated Author", "Updated Genre", "2024-01-01", 10);
        DatabaseConnectionTDD.updateBook(book);  // Assuming you have an updateBook() method

        // Verify the book is updated
        books = DatabaseConnectionTDD.searchBooks("Updated");
        assertEquals(1, books.size(), "Book should be updated.");
        assertEquals("Updated", books.get(0).getTitle());
        assertEquals(10, books.get(0).getStock());

        // Step 3: Delete the book
        boolean isDeleted = DatabaseConnectionTDD.deleteBook("978-3-16-148410-3");
        assertTrue(isDeleted, "Book should be deleted.");

        // Verify the book is deleted
        books = DatabaseConnectionTDD.searchBooks("Updated");
        assertEquals(0, books.size(), "Book should be deleted.");
    }

    // Test case 2: Add and retrieve multiple books
    @Test
    public void testAddAndRetrieveMultipleBooks() {
        // Add multiple books
        BookTDD book1 = new BookTDD("978-3-16-148410-1", "A Book One", "Author One", "Genre One", "2023-01-01", 3);
        BookTDD book2 = new BookTDD("978-3-16-148410-2", "A Book Two", "Author Two", "Genre Two", "2023-02-01", 7);
        DatabaseConnectionTDD.addBook(book1);
        DatabaseConnectionTDD.addBook(book2);

        // Retrieve all books and verify
        List<BookTDD> books = DatabaseConnectionTDD.getBooks("Title A-Z");  // Assuming getBooks() retrieves all books
        assertTrue(books.size() >= 2, "At least two books should be present.");
        assertEquals("A Book One", books.get(0).getTitle());
        assertEquals("A Book Two", books.get(1).getTitle());
    }

    // Test case 3: Try to update a non-existent book
    @Test
    public void testUpdateNonExistentBook() {
        // Try to update a book that doesn't exist
        BookTDD nonExistentBook = new BookTDD("123-4-56-789012-3", "Non Existent Book", "No Author", "No Genre", "2024-01-01", 0);
        boolean isUpdated = DatabaseConnectionTDD.updateBook(nonExistentBook);  // Assuming updateBook returns a boolean
        assertFalse(isUpdated, "Update should fail for non-existent book.");
    }

    // Test case 4: Try to delete a non-existent book
    @Test
    public void testDeleteNonExistentBook() {
        // Try to delete a book that doesn't exist
        boolean isDeleted = DatabaseConnectionTDD.deleteBook("123-4-56-789012-3");
        assertFalse(isDeleted, "Delete should fail for non-existent book.");
    }
}
