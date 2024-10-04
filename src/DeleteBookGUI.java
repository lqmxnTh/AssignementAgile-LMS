import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookGUI extends JFrame {

    private JTextField isbnField;

    public DeleteBookGUI() {
        // Set title
        super("Delete Book");

        // Layout and components
        JLabel isbnLabel = new JLabel("Enter ISBN to Delete: ");
        isbnField = new JTextField(15);
        JButton deleteButton = new JButton("Delete Book");

        JPanel panel = new JPanel();
        panel.add(isbnLabel);
        panel.add(isbnField);
        panel.add(deleteButton);

        add(panel);

        // Add action listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        // Window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }

    private void deleteBook() {
        String isbn = isbnField.getText().trim();  // Trim any leading/trailing spaces

        // Validate ISBN format based on the general ISBN-13 format
        if (!isValidISBN(isbn)) {
            JOptionPane.showMessageDialog(this, "Invalid ISBN format. Please provide a valid ISBN.");
            return;
        }

        // Proceed to delete book
        try {
            boolean success = DatabaseConnection.deleteBook(isbn);

            if (success) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Book not found or could not be deleted.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the field after deletion attempt
        isbnField.setText("");
    }

    private boolean isValidISBN(String isbn) {
        // Regex explanation:
        // ^\\d{3}-        : Start with any 3 digits followed by a hyphen
        // \\d{1}-         : One digit for the group identifier followed by a hyphen
        // \\d{2}-         : Two digits for the publisher code followed by a hyphen
        // \\d{6}-         : Six digits for the title identifier followed by a hyphen
        // \\d{1}$         : One digit for the check digit at the end
        // No spaces allowed, just digits and hyphens in the specified format.

        String isbnPattern = "^\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }
}
