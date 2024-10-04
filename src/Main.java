import java.sql.Connection;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {

            // Create a simple menu to choose between adding or deleting a book
            String[] options = {"Add Book", "Delete Book"};
            int choice = JOptionPane.showOptionDialog(null, "Select an action:", "Book Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                new AddBookGUI();
            }
            else if (choice == 1) {
                new DeleteBookGUI();
            }
        }
        else {
            System.out.println("Failed to connect to the database.");
        }

    }
}