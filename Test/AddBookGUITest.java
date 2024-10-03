import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddBookGUITest {

    private final AddBookGUI addBookGUI = new AddBookGUI();

    @Test
    public void testValidISBN() {
        assertTrue(addBookGUI.isValidISBN("123-456-789"));
        assertTrue(addBookGUI.isValidISBN("9876543210"));
        assertFalse(addBookGUI.isValidISBN("ABC-123"));  // Invalid characters
        assertFalse(addBookGUI.isValidISBN(""));         // Empty string
        assertFalse(addBookGUI.isValidISBN(null));       // Null value
    }

    @Test
    public void testValidTitle() {
        assertTrue(addBookGUI.isValidTitle("The Great Book"));
        assertTrue(addBookGUI.isValidTitle("Book' Title")); // Apostrophe allowed
        assertFalse(addBookGUI.isValidTitle("Title@Book")); // Special characters not allowed
        assertFalse(addBookGUI.isValidTitle(""));           // Empty string
        assertFalse(addBookGUI.isValidTitle(null));         // Null value
    }

    @Test
    public void testValidAuthor() {
        assertTrue(addBookGUI.isValidAuthor("John Doe"));
        assertTrue(addBookGUI.isValidAuthor("Jane O'Neil"));  // Apostrophe allowed
        assertFalse(addBookGUI.isValidAuthor("John123"));      // Numbers not allowed
        assertFalse(addBookGUI.isValidAuthor(""));            // Empty string
        assertFalse(addBookGUI.isValidAuthor(null));          // Null value
    }

    @Test
    public void testValidGenre() {
        assertTrue(addBookGUI.isValidGenre("Fiction, Adventure"));
        assertTrue(addBookGUI.isValidGenre("Children's Fiction")); // Apostrophe allowed
        assertFalse(addBookGUI.isValidGenre("Sci-Fi@Fantasy"));    // Special characters not allowed
        assertFalse(addBookGUI.isValidGenre(""));                  // Empty string
        assertFalse(addBookGUI.isValidGenre(null));                // Null value
    }

    @Test
    public void testValidStock() {
        assertTrue(addBookGUI.isValidStock("10"));
        assertTrue(addBookGUI.isValidStock("0"));    // Zero is a valid integer
        assertFalse(addBookGUI.isValidStock("10.5")); // Decimals not allowed
        assertFalse(addBookGUI.isValidStock("abc"));  // Non-numeric value
        assertFalse(addBookGUI.isValidStock(""));     // Empty string
        assertFalse(addBookGUI.isValidStock(null));   // Null value
    }
}