import java.util.Comparator;
import java.util.List;

public class BookSortingTDD {

    // Sort by Title (Ascending)
    public static List<BookTDD> sortBooksByTitleAscending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getTitle));
        return books;
    }

    // Sort by Title (Descending)
    public static List<BookTDD> sortBooksByTitleDescending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getTitle).reversed());
        return books;
    }

    // Sort by Author (Ascending)
    public static List<BookTDD> sortBooksByAuthorAscending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getAuthor));
        return books;
    }

    // Sort by Author (Descending)
    public static List<BookTDD> sortBooksByAuthorDescending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getAuthor).reversed());
        return books;
    }

    // Sort by Genre (Ascending)
    public static List<BookTDD> sortBooksByGenreAscending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getGenre));
        return books;
    }

    // Sort by Genre (Descending)
    public static List<BookTDD> sortBooksByGenreDescending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getGenre).reversed());
        return books;
    }

    // Sort by Publication Date (Ascending)
    public static List<BookTDD> sortBooksByPublicationDateAscending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getPublishedYear, Comparator.nullsLast(Comparator.naturalOrder())));
        return books;
    }

    // Sort by Publication Date (Descending)
    public static List<BookTDD> sortBooksByPublicationDateDescending(List<BookTDD> books) {
        books.sort(Comparator.comparing(BookTDD::getPublishedYear, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
        return books;
    }

    // Sort by Stock (Ascending)
    public static List<BookTDD> sortBooksByStockAscending(List<BookTDD> books) {
        books.sort(Comparator.comparingInt(BookTDD::getStock));
        return books;
    }

    // Sort by Stock (Descending)
    public static List<BookTDD> sortBooksByStockDescending(List<BookTDD> books) {
        books.sort(Comparator.comparingInt(BookTDD::getStock).reversed());
        return books;
    }
}
