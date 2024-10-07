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
        String isbn = isbnField.getText();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty.");
            return;
        }

        boolean success = DatabaseConnection.deleteBook(isbn);

        if (success) {
            JOptionPane.showMessageDialog(this, "Book deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Book not found or could not be deleted."); // Message when deletion fails
        }

        isbnField.setText("");  // Clear the field after deletion
    }
}
