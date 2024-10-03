import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class AddBookGUI extends JFrame {

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JDateChooser publishedYearPicker;  // Date picker
    private JTextField stockField;

    public AddBookGUI() {
        // Set the title of the window
        super("Add New Book");

        // Set the layout of the frame
        setLayout(new GridLayout(7, 2));  // 7 rows, 2 columns

        // Create the components
        JLabel isbnLabel = new JLabel("ISBN: ");
        isbnField = new JTextField();

        JLabel titleLabel = new JLabel("Title: ");
        titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author: ");
        authorField = new JTextField();

        JLabel genreLabel = new JLabel("Genre: ");
        genreField = new JTextField();

        JLabel publishedYearLabel = new JLabel("Published Year: ");
        publishedYearPicker = new JDateChooser();  // Initialize the date picker
        publishedYearPicker.setDateFormatString("yyyy-MM-dd");  // Set the format to "YYYY-MM-DD"

        JLabel stockLabel = new JLabel("Stock: ");
        stockField = new JTextField();

        JButton addButton = new JButton("Add Book");

        // Add components to the frame
        add(isbnLabel);
        add(isbnField);

        add(titleLabel);
        add(titleField);

        add(authorLabel);
        add(authorField);

        add(genreLabel);
        add(genreField);

        add(publishedYearLabel);
        add(publishedYearPicker);  // Add date picker

        add(stockLabel);
        add(stockField);

        add(new JLabel());  // Empty label to align the button
        add(addButton);

        // Set action listener for the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookToDatabase();
            }
        });

        // Set default window behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);  // Set the window size
        setVisible(true);  // Make the window visible
    }

    // Method to validate ISBN
    public boolean isValidISBN(String isbn) {
        return isbn != null && isbn.matches("[0-9-]+");
    }

    // Method to validate Title
    public boolean isValidTitle(String title) {
        return title != null && title.matches("[a-zA-Z0-9' ]+");
    }

    // Method to validate Author
    public boolean isValidAuthor(String author) {
        return author != null && author.matches("[a-zA-Z' ]+");
    }

    // Method to validate Genre
    public boolean isValidGenre(String genre) {
        return genre != null && genre.matches("[a-zA-Z,' ]+");
    }

    // Method to validate Stock
    public boolean isValidStock(String stock) {
        try {
            Integer.parseInt(stock);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to handle adding the book to the database
    private void addBookToDatabase() {
        try {
            // Get user input from the fields
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();

            // Get the date from the date picker
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String publishedYear = dateFormat.format(publishedYearPicker.getDate());

            String stockStr = stockField.getText();

            // Validate inputs
            if (!isValidISBN(isbn)) {
                throw new Exception("Invalid ISBN format.");
            }

            if (!isValidTitle(title)) {
                throw new Exception("Invalid title format.");
            }

            if (!isValidAuthor(author)) {
                throw new Exception("Invalid author format.");
            }

            if (!isValidGenre(genre)) {
                throw new Exception("Invalid genre format.");
            }

            if (!isValidStock(stockStr)) {
                throw new Exception("Invalid stock format.");
            }

            int stock = Integer.parseInt(stockStr);

            // Create a new Book object
            Book newBook = new Book(isbn, title, author, genre, publishedYear, stock);

            // Add the book to the database (Assuming DatabaseConnection.addBook exists)
            DatabaseConnection.addBook(newBook);

            // Show success message
            JOptionPane.showMessageDialog(this, "Book added successfully!");

            // Clear the fields for the next entry
            clearFields();

        } catch (Exception ex) {
            // Show error message
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear the input fields
    private void clearFields() {
        isbnField.setText("");
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        publishedYearPicker.setDate(null);  // Clear the date picker
        stockField.setText("");
    }

}
