import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnectionTDD.getConnection();
        if (connection != null) {
            System.out.println("Connection to the database established successfully and populated with sample data!");
            // Create a scanner for user input
            new MainMenuTDD();
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}