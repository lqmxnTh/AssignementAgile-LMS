import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuTDD extends JFrame {

    public MainMenuTDD() {
        // Set up the frame
        setTitle("Library Management System - Main Menu");
        setSize(800, 600);  // Frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Library Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Font for the title
        add(titleLabel, BorderLayout.NORTH);

        // Create buttons for different operations
        JButton addBookButton = new JButton("Add a Book");
        JButton deleteBookButton = new JButton("Delete a Book");
        JButton updateBookButton = new JButton("Update a Book");
        JButton searchBookButton = new JButton("Search for a Book");
        JButton displayBooksButton = new JButton("Display Book List");  // The missing button
        JButton exitButton = new JButton("Exit");

        // Set a fixed size for the buttons
        Dimension buttonSize = new Dimension(200, 50);
        addBookButton.setPreferredSize(buttonSize);
        deleteBookButton.setPreferredSize(buttonSize);
        updateBookButton.setPreferredSize(buttonSize);
        searchBookButton.setPreferredSize(buttonSize);
        displayBooksButton.setPreferredSize(buttonSize);  // Set size for displayBooksButton
        exitButton.setPreferredSize(buttonSize);

        // Add buttons to a panel with FlowLayout (centered)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));  // Centering with spacing
        buttonPanel.add(addBookButton);
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(updateBookButton);
        buttonPanel.add(searchBookButton);
        buttonPanel.add(displayBooksButton);  // Add the Display Book List button to the panel
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Action listener for the "Search for a Book" button
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the SearchBook functionality
                new SearchBookTDD();  // Opens the Search Book GUI
            }
        });

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the SearchBook functionality
                new AddBookTDD();  // Opens the Search Book GUI
            }
        });
        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the SearchBook functionality
                new UpdateBookTDD();  // Opens the Search Book GUI
            }
        });
        // Action listener for the "Display Book List" button
        displayBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayBookTDD();  // Opens the Display Book GUI
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteBookTDD();  // Opens the Display Book List GUI
            }
        });

        // Action listener for the "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exits the application
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuTDD();  // Launch the GUI
    }
}
