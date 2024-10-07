import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseConnectionTDD {

    private static final String URL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "";  // If using Windows Authentication, leave this blank
    private static final String PASSWORD = "";  // If using Windows Authentication, leave this blank
    private static final String DB_NAME = "LibraryDB";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Connect to the SQL Server without a specific database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            // SQL to create the database if it does not exist
            String createDBQuery = "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = '" + DB_NAME + "') " +
                    "BEGIN " +
                    "CREATE DATABASE " + DB_NAME + " " +
                    "END";
            // Execute the create database statement
            statement.executeUpdate(createDBQuery);

            // Now connect to the newly created or existing database
            String dbURL = URL + ";databaseName=" + DB_NAME;
            connection.close(); // Close the previous connection

            // Create a new connection to the specific database
            connection = DriverManager.getConnection(dbURL, USER, PASSWORD);
            statement = connection.createStatement(); // Recreate the statement for the new connection

            // Create the books table if it does not exist
            createTable(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void createTable(Statement statement) throws SQLException {
        // SQL to create the books table if it does not exist
        String createTableQuery = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='books' AND xtype='U') " +
                "BEGIN " +
                "CREATE TABLE books (" +
                "id INT PRIMARY KEY IDENTITY(1,1)," +
                "isbn VARCHAR(17)," +  // Change from VARCHAR(13) to VARCHAR(17)
                "title VARCHAR(100)," +
                "author VARCHAR(100)," +
                "genre VARCHAR(50)," +
                "published_year DATE," +
                "stock INT" +
                ");" +
                "END";

        // Execute the create table statement
        statement.executeUpdate(createTableQuery);

        // Check if the books table is empty and populate with sample records if it is
        String checkTableQuery = "SELECT COUNT(*) FROM books";
        var resultSet = statement.executeQuery(checkTableQuery);
        if (resultSet.next() && resultSet.getInt(1) == 0) {
            populateDatabase(statement);
        }
    }


    private static void populateDatabase(Statement statement) throws SQLException {
        // SQL to insert sample records into the books table
        String insertDataQuery = "INSERT INTO books (isbn, title, author, genre, published_year, stock) VALUES " +
                "('978-3-16-148410-0', 'Java Programming', 'John Doe', 'Programming', '2020-01-01', 10), " +
                "('978-1-60309-057-5', 'Effective Java', 'Joshua Bloch', 'Programming', '2018-05-01', 5), " +
                "('978-0-201-83595-0', 'Clean Code', 'Robert C. Martin', 'Programming', '2017-10-01', 8), " +
                "('978-0-321-48681-3', 'Introduction to Algorithms', 'Thomas H. Cormen', 'Algorithms', '2019-08-01', 3), " +
                "('978-0-262-03384-8', 'Artificial Intelligence: A Modern Approach', 'Stuart Russell', 'AI', '2021-06-01', 6)";
        // Execute the insert data statement
        statement.executeUpdate(insertDataQuery);
}


    public static List<BookTDD> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();  // Return an empty list for empty input
        }

        List<BookTDD> books = new ArrayList<>();
        String searchQuery = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            String searchKeyword = "%" + keyword + "%";  // Allows partial matches
            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);
            preparedStatement.setString(3, searchKeyword);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate over the results and create BookTDD objects for each match
            while (resultSet.next()) {
                BookTDD book = new BookTDD(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("genre"),
                        resultSet.getDate("published_year").toString(),  // Convert date to string
                        resultSet.getInt("stock")
                );
                books.add(book);  // Add each matching book to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;  // Return the list of matching books
    }

    public static void addBook(BookTDD book) {
        String insertBookQuery = "INSERT INTO books (isbn, title, author, genre, published_year, stock) VALUES (?, ?, ?, ?, ?, ?)";
        if(fetchBookByIsbn( book.getIsbn() ) != null){
            return;
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setDate(5, Date.valueOf(book.getPublishedYear())); // Convert String to Date
            preparedStatement.setInt(6, book.getStock());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("Failed to add the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<BookTDD> getBooks(String sortOption) {
        List<BookTDD> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        // Modify the SQL query based on the sort option
        switch (sortOption) {
            case "Title A-Z":
                query += " ORDER BY title ASC";
                break;
            case "Title Z-A":
                query += " ORDER BY title DESC";
                break;
            case "Author A-Z":
                query += " ORDER BY author ASC";
                break;
            case "Author Z-A":
                query += " ORDER BY author DESC";
                break;
            case "Genre A-Z":
                query += " ORDER BY genre ASC";
                break;
            case "Genre Z-A":
                query += " ORDER BY genre DESC";
                break;
            case "Stock Ascending":
                query += " ORDER BY stock ASC";
                break;
            case "Stock Descending":
                query += " ORDER BY stock DESC";
                break;
            case "Latest":
                query += " ORDER BY published_year DESC";
                break;
            default:
                break; // No sorting, return unsorted
        }

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Fetch each book from the result set and add to the list
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String publishedYear = resultSet.getString("published_year");
                int stock = resultSet.getInt("stock");

                books.add(new BookTDD(isbn, title, author, genre, publishedYear, stock));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Fetch a book by ISBN from the database
    public static BookTDD fetchBookByIsbn(String isbn) {
        BookTDD book = null;

        // SQL query to select book details by ISBN
        String fetchBookQuery = "SELECT * FROM books WHERE isbn = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(fetchBookQuery)) {

            // Set the ISBN parameter in the prepared statement
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a book is found, extract the details and create a Book object
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String publishedYear = resultSet.getDate("published_year").toString();
                int stock = resultSet.getInt("stock");

                // Create the book object with the fetched details
                book = new BookTDD(isbn, title, author, genre, publishedYear, stock);
            } else {
                System.out.println("No book found with ISBN: " + isbn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }




    // Update a book in the database
    public static boolean updateBook(BookTDD book) {
        // SQL query to update book details based on ISBN
        String updateBookQuery = "UPDATE books SET title = ?, author = ?, genre = ?, published_year = ?, stock = ? WHERE isbn = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery)) {

            // Set the parameters for the prepared statement based on the Book object
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDate(4, Date.valueOf(book.getPublishedYear()));
            preparedStatement.setInt(5, book.getStock());
            preparedStatement.setString(6, book.getIsbn());

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful based on the affected rows
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
                return true;
            } else {
                System.out.println("Failed to update the book.");
            }
        } catch (SQLException e) {
            // Log any SQL exception that occurs
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteBook(String isbn) {
        String deleteQuery = "DELETE FROM books WHERE isbn = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, isbn);

            // Execute delete query and return true if a row was affected (i.e., a book was deleted)
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if there was an error
        }
    }
}

