import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBookTDD extends JFrame {

    JTable bookTable;
    JComboBox<String> sortDropdown;

    public DisplayBookTDD() {
        super("List Of Books");

        // Create a dropdown for sorting
        JLabel sortLabel = new JLabel("Sort by: ");
        String[] sortOptions = {
                "Select",
                "Latest",
                "Title A-Z",
                "Title Z-A",
                "Author A-Z",
                "Author Z-A",
                "Genre A-Z",
                "Genre Z-A",
                "Stock Ascending",
                "Stock Descending"
        };
        sortDropdown = new JComboBox<>(sortOptions);

        // Set layout
        setLayout(new BorderLayout());

        // Add sorting panel
        JPanel sortPanel = new JPanel();
        sortPanel.add(sortLabel);
        sortPanel.add(sortDropdown);

        // Add sorting panel to the top of the frame
        add(sortPanel, BorderLayout.NORTH);

        // Initialize the table for displaying books
        bookTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        // Set action listener for dropdown
        sortDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortDropdown.getSelectedItem();
                if (!selectedOption.equals("Select")) {
                    displayBooks(selectedOption);  // Display books according to selected sort option
                }
            }
        });

        // Load and display books in the default order (Latest)
        displayBooks("Latest"); // Default sorting option

        // Default window settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Method to display books in the table
    void displayBooks(String sortOption) {
        List<BookTDD> books = DatabaseConnectionTDD.getBooks(sortOption);  // Fetch sorted books from the database

        // Column names for the table
        String[] columnNames = {"ISBN", "Title", "Author", "Genre", "Published Year", "Stock"};

        // Data for the table
        String[][] data = new String[books.size()][6];
        for (int i = 0; i < books.size(); i++) {
            BookTDD book = books.get(i);
            data[i][0] = book.getIsbn();
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = book.getGenre();
            data[i][4] = book.getPublishedYear();
            data[i][5] = String.valueOf(book.getStock());
        }

        // Set the new model for the table
        bookTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        new DisplayBookTDD();  // Launch the GUI
    }
}
