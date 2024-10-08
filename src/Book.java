public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String publishedYear; // Date as String in "YYYY-MM-DD" format
    private int stock;

    public Book(String isbn, String title, String author, String genre, String publishedYear, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.stock = stock;
    }

    // Getters for the Book fields
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public int getStock() {
        return stock;
    }
}
