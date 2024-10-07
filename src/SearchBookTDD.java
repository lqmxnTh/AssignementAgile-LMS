

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchBookTDD extends JFrame {

    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public SearchBookTDD() {
        setTitle("Library Management System - Search for a Book");
        setSize(800, 600);  // Frame size
        setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Search for a Book", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Font for the title
        add(titleLabel, BorderLayout.NORTH);

        // Create the search panel for the search box and button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 30));  // Fixed size for search box
        searchPanel.add(new JLabel("Enter keyword:"));
        searchPanel.add(searchField);

        // Create the search button and place it next to the search box
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));  // Set size for the search button
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        // Create the table to display results
        String[] columnNames = {"ISBN", "Title", "Author", "Genre", "Published Date", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0);  // 0 is the initial row count
        resultTable = new JTable(tableModel);
        resultTable.setPreferredScrollableViewportSize(new Dimension(750, 400));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform search
                String keyword = searchField.getText();
                List<BookTDD> books = DatabaseConnectionTDD.searchBooks(keyword);

                // Clear previous results
                tableModel.setRowCount(0);

                // Display results
                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No books found.");
                } else {
                    for (BookTDD book : books) {
                        Object[] row = {
                                book.getIsbn(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getGenre(),
                                book.getPublishedYear(),
                                book.getStock()
                        };
                        tableModel.addRow(row);  // Add each book as a row in the table
                    }
                }
            }
        });

        // Make the frame visible
        setVisible(true);
    }
}
