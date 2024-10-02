import java.util.Scanner;

public class AddBook {
    private Scanner scanner;

    public AddBook() {
        this.scanner = new Scanner(System.in);
    }

    public void execute() {
        System.out.println("Enter the book details:");

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Published Year (YYYY-MM-DD): ");
        String publishedYear = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        // Create a new Book object
        Book newBook = new Book(isbn, title, author, genre, publishedYear, stock);

        // Add the book to the database
        DatabaseConnection.addBook(newBook);
    }
}
