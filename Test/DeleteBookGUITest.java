import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBookGUITest {

    private final DeleteBookGUI deleteBookGUI = new DeleteBookGUI();

    @Test
    public void testValidISBN() {
        assertFalse(deleteBookGUI.isValidISBN(null)); // Null Values
        assertFalse(deleteBookGUI.isValidISBN("")); // Empty field
        assertFalse(deleteBookGUI.isValidISBN("9783161484100")); // Without Hyphens
        assertFalse(deleteBookGUI.isValidISBN("978-3-16-14 8 4 10-0")); // Contains space in between ISBN
        assertFalse(deleteBookGUI.isValidISBN("123-3-YZ-123456-A")); // Contain letters
        assertFalse(deleteBookGUI.isValidISBN("1234-12-268-1234567-01")); // excess number of ISBN characters
        assertTrue(deleteBookGUI.isValidISBN("978-3-16-148410-0"));
    }

}