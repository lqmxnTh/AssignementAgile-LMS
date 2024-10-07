import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateBookTDD extends JFrame {

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JDateChooser publishedYearPicker;
    private JTextField stockField;

    public UpdateBookTDD() {
        super("Update Book");
        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // UI Setup
    private void setupUI() {
        setLayout(new GridLayout(7, 2));

        JLabel isbnLabel = new JLabel("ISBN: ");
        isbnField = new JTextField();

        JLabel titleLabel = new JLabel("Title: ");
        titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author: ");
        authorField = new JTextField();

        JLabel genreLabel = new JLabel("Genre: ");
        genreField = new JTextField();

        JLabel publishedYearLabel = new JLabel("Published Year: ");
        publishedYearPicker = new JDateChooser();
        publishedYearPicker.setDateFormatString("yyyy-MM-dd");

        JLabel stockLabel = new JLabel("Stock: ");
        stockField = new JTextField();

        JButton fetchButton = new JButton("Fetch Book");
        JButton updateButton = new JButton("Update Book");

        // Add components to the UI
        add(isbnLabel);
        add(isbnField);
        add(titleLabel);
        add(titleField);
        add(authorLabel);
        add(authorField);
        add(genreLabel);
        add(genreField);
        add(publishedYearLabel);
        add(publishedYearPicker);
        add(stockLabel);
        add(stockField);
        add(fetchButton);
        add(updateButton);

        // Add action listeners
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchBookDetails();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookInDatabase();
            }
        });
    }

    // Validation Methods
    public boolean isValidISBN(String isbn) {
        return isbn != null && isbn.matches("[0-9-]+") && isbn.replace("-", "").length() == 13;
    }

    // Method to validate Title
    public boolean isValidTitle(String title) {
        return title != null && title.matches("[a-zA-Z0-9' ]+") && title != " " ;
    }

    // Method to validate Author
    public boolean isValidAuthor(String author) {
        return author != null && author.matches("[a-zA-Z-' ]+");
    }

    // Method to validate Genre
    public boolean isValidGenre(String genre) {
        return genre != null && genre.matches("[a-zA-Z-,' ]+");
    }

    // Method to validate Stock
    public boolean isValidStock(String stock) {
        try {
            Integer.parseInt(stock);
            if (Integer.parseInt(stock) > -1) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Fetch Book Details (from DB)
    private void fetchBookDetails() {
        String isbn = isbnField.getText();
        if (isValidISBN(isbn)) {
            BookTDD book = DatabaseConnectionTDD.fetchBookByIsbn(isbn);
            if (book != null) {
                fillBookDetails(book);
            } else {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ISBN format.");
        }
    }

    // Helper method to fill fields with book details
    private void fillBookDetails(BookTDD book) {
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        genreField.setText(book.getGenre());

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(book.getPublishedYear());
            publishedYearPicker.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        stockField.setText(String.valueOf(book.getStock()));
    }

    // Update Book in the Database
    private void updateBookInDatabase() {
        try {
            // Fetch data from input fields
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String publishedYear = dateFormat.format(publishedYearPicker.getDate());
            int stock = Integer.parseInt(stockField.getText());

            // Validate inputs using the tested methods
            validateInputFields(isbn, title, author, genre, stock);

            // Update book in the database
            BookTDD updatedBook = new BookTDD(isbn, title, author, genre, publishedYear, stock);
            DatabaseConnectionTDD.updateBook(updatedBook);

            JOptionPane.showMessageDialog(this, "Book updated successfully!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to validate input fields before updating the book
    private void validateInputFields(String isbn, String title, String author, String genre, int stock) throws Exception {
        if (!isValidISBN(isbn)) throw new Exception("Invalid ISBN format.");
        if (!isValidTitle(title)) throw new Exception("Invalid title format.");
        if (!isValidAuthor(author)) throw new Exception("Invalid author format.");
        if (!isValidGenre(genre)) throw new Exception("Invalid genre format.");
        if (!isValidStock(String.valueOf(stock))) throw new Exception("Invalid stock format.");
    }
}
