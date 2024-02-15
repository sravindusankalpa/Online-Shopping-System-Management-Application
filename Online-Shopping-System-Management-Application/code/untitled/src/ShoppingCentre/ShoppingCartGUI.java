// Import necessary libraries
package ShoppingCentre;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// Main class for the Shopping Cart GUI
public class ShoppingCartGUI extends JFrame {

    // GUI components
    private JTable cartTable;
    private ShoppingCart shoppingCart;
    private JLabel totalLabel;
    private JLabel discount2Label;
    private JLabel discountLabel;
    private JLabel finalTotalLabel;

    // Constructor
    public ShoppingCartGUI(ShoppingCart shoppingCart) {
        setTitle("Shopping Cart");
        setSize(700, 500);
        this.shoppingCart = shoppingCart;

        initializeUI(); // Initialize the GUI components
    }

    // Method to set up the initial state of the GUI
    private void initializeUI() {
        cartTable = new JTable();
        updateCartTable(); // Update the cart table with initial data

        createLabels(); // Create labels for total, discounts, and final total

        JPanel tablePanel = createTablePanel(); // Create a panel for the cart table
        JPanel totalPanel = createTotalPanel(); // Create a panel for total, discounts, and final total labels


        // Use BorderLayout to arrange components
        setLayout(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);
        add(totalPanel, BorderLayout.SOUTH);

        // Set the frame size after adding components
        setSize(500, 500);
        cartTable.setRowHeight(30); // Adjust the height as needed
    }

    // Method to create labels for total, discounts, and final total
    private void createLabels() {
        totalLabel = createLabel("Total :");
        discount2Label = createLabel("First purchase Discount (10%) :");
        discountLabel = createLabel("Three items in the same Category Discount (20%) :");
        finalTotalLabel = createLabel("Final Total :");
    }

    // Helper method to create a label with specific formatting
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBorder(new EmptyBorder(0, 30, 0, 0));
        return label;
    }

    // Method to create a panel for the cart table
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBorder(new EmptyBorder(40, 50, 40, 50));
        tablePanel.add(scrollPane, BorderLayout.NORTH);
        return tablePanel;
    }

    // Method to create a panel for total, discounts, and final total labels
    private JPanel createTotalPanel() {
        JPanel totalPanel = new JPanel(new GridLayout(0, 1)); // Adjust rows, columns, and gaps as needed

        // Display the total price
        double total = shoppingCart.calculateTotal();
        JLabel totalLabel = new JLabel("Total : " + String.format("%.2f£", total));
        totalPanel.add(totalLabel);

        // Display the first purchase discount
        double discount10 = shoppingCart.calculateDiscount10();
        JLabel discount2Label = new JLabel("First purchase Discount (10%) : - " + String.format("%.2f£", discount10));
        totalPanel.add(discount2Label);

        // Display the category discount
        double discount20 = shoppingCart.calculateDiscount20();
        JLabel discountLabel = new JLabel("Three items in the same Category Discount (20%) : - " + String.format("%.2f£", discount20));
        totalPanel.add(discountLabel);

        // Display the final total after discounts
        double finalTotal = shoppingCart.calculateFinalTotal();
        JLabel finalTotalLabel = new JLabel("Final Total : " + String.format("%.2f£", finalTotal));
        totalPanel.add(finalTotalLabel);

        totalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add the totalPanel to the main frame or another container

        return totalPanel;
    }

    // Method to set up the panel with buttons (e.g., Remove button)


    // Method to remove the selected product from the shopping cart


    // Method to update the cart table with current shopping cart data
    private void updateCartTable() {
        // Create a table model for the cart table
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Object.class : String.class;
            }
        };
        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Price");

        // Get product quantity map from the shopping cart
        Map<Object, Integer> productQuantity = shoppingCart.getProductQuantity();
        if (productQuantity != null) {
            for (Map.Entry<Object, Integer> entry : productQuantity.entrySet()) {
                Product product = (Product) entry.getKey();
                int count = (int) entry.getValue();
                // Add product information to the table model
                model.addRow(new Object[]{product.getProductId() + "\n" + product.getProductName(), count, product.getPrice()});
            }
        }

        // Set the table model for the cart table and set renderer for the "Product" column
        cartTable.setModel(model);
        setMultiLineCellRenderer(cartTable, 0);
    }

    // Method to set a renderer for the "Product" column to handle multiline content
    private void setMultiLineCellRenderer(JTable table, int column) {
        TableColumn productColumn = table.getColumnModel().getColumn(column);
        productColumn.setCellRenderer(new MultiLineCellRenderer());
    }

    // Method to update the final total labels based on the current shopping cart state
    private void updateFinalTotal() {
        // Update the final total labels based on the current shopping cart state
        double total = shoppingCart.calculateTotal();
        double discount10 = shoppingCart.calculateDiscount10();
        double discount20 = shoppingCart.calculateDiscount20();
        double finalTotal = shoppingCart.calculateFinalTotal();

        totalLabel.setText("Total : " + String.format("%.2f£", total));
        discount2Label.setText("First purchase Discount (10%) : - " + String.format("%.2f£", discount10));
        discountLabel.setText("Three items in the same Category Discount (20%) : - " + String.format("%.2f£", discount20));
        finalTotalLabel.setText("Final Total : " + String.format("%.2f£", finalTotal));
    }

    // Inner class for rendering multiline content in a table cell
    private static class MultiLineCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                // Replace newline characters with HTML line breaks
                String text = value.toString().replaceAll("\n", "<br>");
                return super.getTableCellRendererComponent(table, "<html>" + text + "</html>", isSelected, hasFocus, row, column);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingCartGUI(new ShoppingCart()));
    }
}
