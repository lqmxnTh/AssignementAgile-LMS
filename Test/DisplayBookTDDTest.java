import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayBookTDDTest {

    // Test sorting books by title in ascending order
    @Test
    public void testSortBooksByTitleAscending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleAscending(books);

        assertEquals("Artificial Intelligence", sortedBooks.get(0).getTitle());
        assertEquals("Clean Code", sortedBooks.get(1).getTitle());
        assertEquals("Java Programming", sortedBooks.get(2).getTitle());
    }

    // Test sorting books by title in descending order
    @Test
    public void testSortBooksByTitleDescending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleDescending(books);

        assertEquals("Java Programming", sortedBooks.get(0).getTitle());
        assertEquals("Clean Code", sortedBooks.get(1).getTitle());
        assertEquals("Artificial Intelligence", sortedBooks.get(2).getTitle());
    }

    // Test sorting books by author in ascending order
    @Test
    public void testSortBooksByAuthorAscending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByAuthorAscending(books);

        assertEquals("Joshua Bloch", sortedBooks.get(0).getAuthor());
        assertEquals("Robert C. Martin", sortedBooks.get(1).getAuthor());
        assertEquals("Stuart Russell", sortedBooks.get(2).getAuthor());
    }

    // Test sorting books by author in descending order
    @Test
    public void testSortBooksByAuthorDescending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByAuthorDescending(books);

        assertEquals("Stuart Russell", sortedBooks.get(0).getAuthor());
        assertEquals("Robert C. Martin", sortedBooks.get(1).getAuthor());
        assertEquals("Joshua Bloch", sortedBooks.get(2).getAuthor());
    }

    // Test sorting books by genre in ascending order
    @Test
    public void testSortBooksByGenreAscending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByGenreAscending(books);

        assertEquals("AI", sortedBooks.get(0).getGenre());
        assertEquals("Programming", sortedBooks.get(1).getGenre());
        assertEquals("Programming", sortedBooks.get(2).getGenre());
    }

    // Test sorting books by genre in descending order
    @Test
    public void testSortBooksByGenreDescending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByGenreDescending(books);

        assertEquals("Programming", sortedBooks.get(0).getGenre());
        assertEquals("Programming", sortedBooks.get(1).getGenre());
        assertEquals("AI", sortedBooks.get(2).getGenre());
    }

    // Test sorting books by publication date in ascending order
    @Test
    public void testSortBooksByPublicationDateAscending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByPublicationDateAscending(books);

        assertEquals("2017-10-01", sortedBooks.get(0).getPublishedYear());
        assertEquals("2020-01-01", sortedBooks.get(1).getPublishedYear());
        assertEquals("2021-06-01", sortedBooks.get(2).getPublishedYear());
    }

    // Test sorting books by publication date in descending order
    @Test
    public void testSortBooksByPublicationDateDescending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByPublicationDateDescending(books);

        assertEquals("2021-06-01", sortedBooks.get(0).getPublishedYear());
        assertEquals("2020-01-01", sortedBooks.get(1).getPublishedYear());
        assertEquals("2017-10-01", sortedBooks.get(2).getPublishedYear());
    }

    // Test sorting books by stock quantity in ascending order
    @Test
    public void testSortBooksByStockAscending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByStockAscending(books);

        assertEquals(6, sortedBooks.get(0).getStock());
        assertEquals(8, sortedBooks.get(1).getStock());
        assertEquals(10, sortedBooks.get(2).getStock());
    }

    // Test sorting books by stock quantity in descending order
    @Test
    public void testSortBooksByStockDescending() {
        List<BookTDD> books = getSampleBooks();
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByStockDescending(books);

        assertEquals(10, sortedBooks.get(0).getStock());
        assertEquals(8, sortedBooks.get(1).getStock());
        assertEquals(6, sortedBooks.get(2).getStock());
    }

    // Test sorting an empty list of books
    @Test
    public void testSortBooksWithEmptyList() {
        List<BookTDD> books = new ArrayList<>();  // Empty list
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleAscending(books);

        assertTrue(sortedBooks.isEmpty(), "Sorting an empty list should return an empty list.");
    }

    // Test sorting a list with a single book
    @Test
    public void testSortBooksWithSingleBook() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "Artificial Intelligence", "Stuart Russell", "AI", "2021-06-01", 6));

        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleAscending(books);

        assertEquals(1, sortedBooks.size(), "List should still contain only one book.");
        assertEquals("Artificial Intelligence", sortedBooks.get(0).getTitle());
    }

    // Test sorting books with duplicate titles
    @Test
    public void testSortBooksWithDuplicateTitles() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "Java Programming", "Author A", "Programming", "2021-06-01", 5));
        books.add(new BookTDD("978-0-201-83595-0", "Java Programming", "Author B", "Programming", "2020-01-01", 8));

        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleAscending(books);

        assertEquals(2, sortedBooks.size(), "Both books should remain in the sorted list.");
        assertEquals("Author A", sortedBooks.get(0).getAuthor(), "Author A should be first as titles are the same.");
        assertEquals("Author B", sortedBooks.get(1).getAuthor(), "Author B should be second as titles are the same.");
    }

    // Test sorting books with null publication dates
    @Test
    public void testSortBooksByPublicationDateWithNullValues() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "Artificial Intelligence", "Stuart Russell", "AI", null, 6));
        books.add(new BookTDD("978-0-201-83595-0", "Clean Code", "Robert C. Martin", "Programming", "2017-10-01", 8));

        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByPublicationDateAscending(books);

        assertNull(sortedBooks.get(1).getPublishedYear(), "Book with null publication date should be last in ascending order.");

        sortedBooks = BookSortingTDD.sortBooksByPublicationDateDescending(books);
        assertNull(sortedBooks.get(0).getPublishedYear(), "Book with null publication date should be first in descending order.");
    }

    // Test sorting books by stock with equal values
    @Test
    public void testSortBooksByStockWithEqualValues() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "Book A", "Author A", "AI", "2021-06-01", 10));
        books.add(new BookTDD("978-0-201-83595-0", "Book B", "Author B", "Programming", "2020-01-01", 10));

        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByStockDescending(books);

        assertEquals(2, sortedBooks.size(), "Both books should be in the sorted list.");
        assertEquals("Book A", sortedBooks.get(0).getTitle(), "Original order should be maintained for equal stock values.");
        assertEquals("Book B", sortedBooks.get(1).getTitle(), "Original order should be maintained for equal stock values.");
    }

    // Test sorting books with mixed case titles
    @Test
    public void testSortBooksByTitleWithMixedCase() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "java Programming", "Author A", "Programming", "2021-06-01", 10));
        books.add(new BookTDD("978-0-201-83595-0", "Java Programming", "Author B", "Programming", "2020-01-01", 10));
        List<BookTDD> sortedBooks = BookSortingTDD.sortBooksByTitleAscending(books);
        assertEquals("Java Programming", sortedBooks.get(0).getTitle(), "Case-insensitive sorting should place capitalized title first.");
        assertEquals("java Programming", sortedBooks.get(1).getTitle(), "Lowercase title should come after in case-insensitive sort.");
    }


    // Helper method to create sample books for testing
    private List<BookTDD> getSampleBooks() {
        List<BookTDD> books = new ArrayList<>();
        books.add(new BookTDD("978-0-262-03384-8", "Artificial Intelligence", "Stuart Russell", "AI", "2021-06-01", 6));
        books.add(new BookTDD("978-0-201-83595-0", "Clean Code", "Robert C. Martin", "Programming", "2017-10-01", 8));
        books.add(new BookTDD("978-3-16-148410-0", "Java Programming", "Joshua Bloch", "Programming", "2020-01-01", 10));
        return books;
    }


}
