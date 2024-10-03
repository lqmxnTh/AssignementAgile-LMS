import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection to the database established successfully and populated with sample data!");
            // PUT CRUD OPERATION HERE
            new AddBookGUI();
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}