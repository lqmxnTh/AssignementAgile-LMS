import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433"; // Use localhost
    private static final String DB_NAME = "LibraryDB"; // Replace with your DB name
    private static final String USER = "sa"; // SQL Server username
    private static final String PASSWORD = "P@ssw0rd";

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

    public static void addBook(Book book) {
        String insertBookQuery = "INSERT INTO books (isbn, title, author, genre, published_year, stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setDate(5, java.sql.Date.valueOf(book.getPublishedYear())); // Convert String to Date
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
}
