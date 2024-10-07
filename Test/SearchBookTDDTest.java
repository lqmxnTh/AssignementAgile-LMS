import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchBookTDDTest {

    @Test
    public void testSearchBooks_withValidKeyword() {
        String keyword = "Java";
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        // Assert that books are returned for the valid keyword
        assertFalse(results.isEmpty(), "Books should be returned for the keyword 'Java'.");

        // Optionally, check if a specific book is returned
        assertEquals("Java Programming", results.get(0).getTitle(), "The first book title should match 'Java Programming'.");
    }

    @Test
    public void testSearchBooks_withInvalidKeyword() {
        String keyword = "NonExistentBook";
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        // Assert that no books are returned for an invalid keyword
        assertTrue(results.isEmpty(), "No books should be returned for the keyword 'NonExistentBook'.");
    }

    @Test
    public void testSearchBooks_withEmptyKeyword() {
        String keyword = "";
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        // Assert that no books are returned for an empty keyword
        assertTrue(results.isEmpty(), "No books should be returned for an empty search.");
    }

    @Test
    public void testSearchBooks_withNullKeyword() {
        String keyword = null;
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertTrue(results.isEmpty(), "No books should be returned for a null search.");
    }


    @Test
    public void testSearchBooks_caseInsensitive() {
        String keyword = "java";  // Lowercase
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertFalse(results.isEmpty(), "Books should be returned for the keyword 'java' (case insensitive).");
        assertEquals("Java Programming", results.get(0).getTitle(), "The first book title should match 'Java Programming'.");
    }

    @Test
    public void testSearchBooks_exactMatch() {
        String keyword = "Java Programming";  // Exact match with a book title
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertFalse(results.isEmpty(), "Books should be returned for the exact match keyword 'Java Programming'.");
        assertEquals("Java Programming", results.get(0).getTitle(), "The first book title should match 'Java Programming'.");
    }

    @Test
    public void testSearchBooks_partialMatch() {
        String keyword = "Pro";  // Part of the word "Programming"
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertFalse(results.isEmpty(), "Books should be returned for the partial match keyword 'Pro'.");
    }

    @Test
    public void testSearchBooks_multipleResults() {
        String keyword = "Java";  // Assume multiple books with this keyword
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);
        System.out.println(results);
        assertTrue(results.size() > 1, "Multiple books should be returned for the keyword 'Programming'.");
    }

    @Test
    public void testSearchBooks_byISBN() {
        String isbn = "978-3-16-148410-0";  // Exact match with a book ISBN
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(isbn);

        assertFalse(results.isEmpty(), "Books should be returned for the ISBN '978-3-16-148410-0'.");
        assertEquals("Java Programming", results.get(0).getTitle(), "The book title should match 'Java Programming'.");
    }

    @Test
    public void testSearchBooks_byAuthor() {
        String author = "John Doe";  // Exact match with the author
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(author);

        assertFalse(results.isEmpty(), "Books should be returned for the author 'John Doe'.");
        assertEquals("Java Programming", results.get(0).getTitle(), "The book title should match 'Java Programming'.");
    }

    @Test
    public void testSearchBooks_withLargeKeyword() {
        String keyword = "ThisIsAVeryLongSearchKeywordThatProbablyDoesNotExist";
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertTrue(results.isEmpty(), "No books should be returned for a very large keyword.");
    }

    @Test
    public void testSearchBooks_withSpecialCharacters() {
        String keyword = "!@#$%^&*()_+";
        List<BookTDD> results = DatabaseConnectionTDD.searchBooks(keyword);

        assertTrue(results.isEmpty(), "No books should be returned for a keyword with special characters.");
    }


}
