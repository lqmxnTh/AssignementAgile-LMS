import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookTDD extends JFrame {

    private JTextField isbnField;

    public DeleteBookTDD() {
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
        String isbn = isbnField.getText().trim();

        // Check if the ISBN is valid
        if (!isValidISBN(isbn)) {
            JOptionPane.showMessageDialog(this, "Invalid ISBN format. Please provide a valid ISBN.");
            return;
        }

        // Try to delete the book from the database
        try {
            boolean success = DatabaseConnection.deleteBook(isbn);

            if (success) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Book not found or could not be deleted.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the ISBN field after the operation
        isbnField.setText("");
    }

    // ISBN validation logic
    public boolean isValidISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return false; // Null or empty ISBN is invalid
        }
        return isbn.matches("^\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}$"); // Valid format check
    }
}


