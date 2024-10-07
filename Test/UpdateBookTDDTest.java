import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookTDDTest {

    private final UpdateBookTDD updateBookGUI = new UpdateBookTDD();

    @Test
    public void testValidISBN() {
        assertTrue(updateBookGUI.isValidISBN("456-456-123-1212"));
        assertFalse(updateBookGUI.isValidISBN("5736475865"));
        assertFalse(updateBookGUI.isValidISBN("FGE-654"));
        assertFalse(updateBookGUI.isValidISBN(""));
        assertFalse(updateBookGUI.isValidISBN(null));
    }

    @Test
    public void testValidTitle() {
        assertTrue(updateBookGUI.isValidTitle("The One and Only"));
        assertTrue(updateBookGUI.isValidTitle("He's Him"));
        assertFalse(updateBookGUI.isValidTitle("Subtitle$@Copybook"));
        assertFalse(updateBookGUI.isValidTitle(""));
        assertFalse(updateBookGUI.isValidTitle(null));
    }

    @Test
    public void testValidAuthor() {
        assertTrue(updateBookGUI.isValidAuthor("Roman Rings"));
        assertTrue(updateBookGUI.isValidAuthor("Ten O'le"));
        assertFalse(updateBookGUI.isValidAuthor("James69"));
        assertFalse(updateBookGUI.isValidAuthor(""));
        assertFalse(updateBookGUI.isValidAuthor(null));
    }

    @Test
    public void testValidGenre() {
        assertTrue(updateBookGUI.isValidGenre("Romance, Dark Comedy"));
        assertTrue(updateBookGUI.isValidGenre("Adult's Fiction"));
        assertFalse(updateBookGUI.isValidGenre("Sci-Fi@Fantasy"));
        assertFalse(updateBookGUI.isValidGenre(""));
        assertFalse(updateBookGUI.isValidGenre(null));
    }

    @Test
    public void testValidStock() {
        assertTrue(updateBookGUI.isValidStock("8"));
        assertTrue(updateBookGUI.isValidStock("0"));
        assertFalse(updateBookGUI.isValidStock("17.5"));
        assertFalse(updateBookGUI.isValidStock("fgh"));
        assertFalse(updateBookGUI.isValidStock(""));
        assertFalse(updateBookGUI.isValidStock(null));
    }
    // Test for updating the book information in the database
    @Test
    public void testUpdateBookInDatabase() {
        // Create a book object with the updated information
        BookTDD updatedBook = new BookTDD("978-1-60309-057-5", "The One and Only", "Roman Rings", "Romance, Dark Comedy", "2022-01-01", 8);

        // Check that the book has valid attributes before updating
        assertTrue(updateBookGUI.isValidISBN(updatedBook.getIsbn()));
        assertTrue(updateBookGUI.isValidTitle(updatedBook.getTitle()));
        assertTrue(updateBookGUI.isValidAuthor(updatedBook.getAuthor()));
        assertTrue(updateBookGUI.isValidGenre(updatedBook.getGenre()));
        assertTrue(updateBookGUI.isValidStock(String.valueOf(updatedBook.getStock())));

        // Perform the update in the database
        DatabaseConnectionTDD.updateBook(updatedBook);

        // Fetch the updated book from the database and verify the updated details
        BookTDD fetchedBook = DatabaseConnectionTDD.fetchBookByIsbn(updatedBook.getIsbn());
        assertNotNull(fetchedBook);  // Ensure the book is not null
        assertEquals("The One and Only", fetchedBook.getTitle());
        assertEquals("Roman Rings", fetchedBook.getAuthor());
        assertEquals("Romance, Dark Comedy", fetchedBook.getGenre());
        assertEquals("2022-01-01", fetchedBook.getPublishedYear());
        assertEquals(8, fetchedBook.getStock());
    }
}
