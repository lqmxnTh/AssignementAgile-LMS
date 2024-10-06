import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddBookGUITest {

    private final AddBookGUI addBookGUI = new AddBookGUI();

   @Test
public void testValidISBN() {
    assertTrue(addBookGUI.isValidISBN("123-456-789"));
    assertTrue(addBookGUI.isValidISBN("9876543210"));
    assertTrue(addBookGUI.isValidISBN("978-3-16-148410-0")); // ISBN-13 valid format
    assertFalse(addBookGUI.isValidISBN("ABC-123"));        // Invalid characters
    assertFalse(addBookGUI.isValidISBN(""));               // Empty string
    assertFalse(addBookGUI.isValidISBN(null));             // Null value
    assertFalse(addBookGUI.isValidISBN("12345"));          // Too short
    assertFalse(addBookGUI.isValidISBN("978-3-16-148410")); // Too short for ISBN-13
}

@Test
public void testValidTitle() {
    assertTrue(addBookGUI.isValidTitle("The Great Book"));
    assertTrue(addBookGUI.isValidTitle("Book' Title"));     // Apostrophe allowed
    assertTrue(addBookGUI.isValidTitle("A Very Long Title That Exceeds Normal Limits But Is Valid"));
    assertTrue(addBookGUI.isValidTitle("T"));               // Single character boundary case
    assertFalse(addBookGUI.isValidTitle("Title@Book"));     // Special characters not allowed
    assertFalse(addBookGUI.isValidTitle(""));               // Empty string
    assertFalse(addBookGUI.isValidTitle(null));             // Null value
    assertFalse(addBookGUI.isValidTitle("A!@#Book Title")); // Special characters
    assertFalse(addBookGUI.isValidTitle(" "));              // Spaces only
}

@Test
public void testValidAuthor() {
    assertTrue(addBookGUI.isValidAuthor("John Doe"));
    assertTrue(addBookGUI.isValidAuthor("Jane O'Neil"));  // Apostrophe allowed
    assertTrue(addBookGUI.isValidAuthor("Jean-Luc Picard")); // Hyphen allowed
    assertTrue(addBookGUI.isValidAuthor("J K Rowling"));  // Initials and spaces
    assertFalse(addBookGUI.isValidAuthor("John123"));      // Numbers not allowed
    assertFalse(addBookGUI.isValidAuthor(""));             // Empty string
    assertFalse(addBookGUI.isValidAuthor(null));           // Null value
    assertFalse(addBookGUI.isValidAuthor("Jane! Doe"));    // Special characters
}

@Test
public void testValidGenre() {
    assertTrue(addBookGUI.isValidGenre("Fiction, Adventure"));
    assertTrue(addBookGUI.isValidGenre("Children's Fiction")); // Apostrophe allowed
    assertTrue(addBookGUI.isValidGenre("Non-Fiction"));        // Hyphen allowed
    assertTrue(addBookGUI.isValidGenre("Sci-Fi"));             // Abbreviated genre
    assertFalse(addBookGUI.isValidGenre("Sci-Fi@Fantasy"));    // Special characters not allowed
    assertFalse(addBookGUI.isValidGenre(""));                  // Empty string
    assertFalse(addBookGUI.isValidGenre(null));                // Null value
}

@Test
public void testValidStock() {
    assertTrue(addBookGUI.isValidStock("10"));
    assertTrue(addBookGUI.isValidStock("0"));                 // Zero is valid
    assertTrue(addBookGUI.isValidStock(Integer.toString(Integer.MAX_VALUE))); // Upper boundary case
    assertFalse(addBookGUI.isValidStock("10.5"));             // Decimals not allowed
    assertFalse(addBookGUI.isValidStock("abc"));              // Non-numeric value
    assertFalse(addBookGUI.isValidStock(""));                 // Empty string
    assertFalse(addBookGUI.isValidStock(null));               // Null value
    assertFalse(addBookGUI.isValidStock("-5"));               // Negative number
    assertFalse(addBookGUI.isValidStock("99999999999999999999999999")); // Overflow case
}

}
