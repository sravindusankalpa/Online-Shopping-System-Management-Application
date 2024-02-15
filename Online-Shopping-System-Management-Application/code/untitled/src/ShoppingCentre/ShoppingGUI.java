package ShoppingCentre;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ShoppingGUI extends JFrame {

    // Managers for shopping and product information
    private ShoppingManager shoppingManager;
    private ShoppingCart shoppingCart;

    // Components for user interface
    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private JTextArea productDetailsArea;
    private JButton addToCartButton;
    private JButton viewShoppingCartButton;

    // Panels for organizing components
    private JPanel topPanel, mainPanel,tablePanel;

    // Label for selecting product category
    private JLabel topLabel;




    // Constructor for creating the GUI
    public ShoppingGUI() {
        // Initialize managers and set up the frame
        shoppingManager = new WestminsterShoppingManager();
        shoppingManager.readProductsFromFile(); // Read products from file at the start of the application
        shoppingCart = new ShoppingCart();

        setTitle("Westminster Shopping Centre");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components for selecting product category
        topLabel = new JLabel("Select Product Category");
        productTypeComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        productTypeComboBox.addActionListener(e -> updateTableData());

        // Table for displaying product information
        DefaultTableModel tableModel = new DefaultTableModel();
        productTable = new JTable(tableModel);
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price($)");
        tableModel.addColumn("Details");

        JScrollPane scrollPane = new JScrollPane(productTable);
        productTable.setPreferredSize(new Dimension(1000, 300));
        scrollPane.setPreferredSize(new Dimension(1000, 300));

        // Text area for displaying product details
        productDetailsArea = new JTextArea();
        productDetailsArea.setEditable(false);
        productDetailsArea.setPreferredSize(new Dimension(500, 200));

        // Buttons for adding to cart and viewing the shopping cart
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> addToCart());
        addToCartButton.setPreferredSize(new Dimension(120, 30));

        // Buttons for  viewing the shopping cart
        viewShoppingCartButton = new JButton("Shopping Cart");
        viewShoppingCartButton.addActionListener(e -> openShoppingCart());

        // Panels for organizing components
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(topLabel);
        topPanel.add(productTypeComboBox);
        topPanel.add(viewShoppingCartButton);

        tablePanel=new JPanel(new BorderLayout());
        tablePanel.add(scrollPane,BorderLayout.NORTH);
        tablePanel.add(productDetailsArea,BorderLayout.CENTER);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addToCartButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        // Add ListSelectionListener to productTable
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Handle row selection event
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Display product details in productDetailsArea
                        Product selectedProduct = shoppingManager.getProductList().get(selectedRow);
                        displayProductDetails(selectedProduct);
                    }
                }
            }
        });

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // Method for displaying product details in the text area
    private void displayProductDetails(Product product) {
        productDetailsArea.setText(productDetailsToString(product));

    }

    // Method for updating the table data based on the selected product category
    private void updateTableData() {
        String selectedProductType = (String) productTypeComboBox.getSelectedItem();
        List<Product> filteredProducts;

        if (selectedProductType.equals("All")) {
            filteredProducts = shoppingManager.getProductList();
        } else if (selectedProductType.equals("Electronics")) {
            filteredProducts = shoppingManager.getElectronicsProducts();
        } else if (selectedProductType.equals("Clothes")) {
            filteredProducts = shoppingManager.getClothingProducts();
        } else {
            filteredProducts = shoppingManager.getProductList(); // Default to all products
        }

        DefaultTableModel tableModel = (DefaultTableModel) productTable.getModel();
        tableModel.setRowCount(0);

        for (Product product : filteredProducts) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(product.getProductId());
            rowData.add(product.getProductName());

            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                rowData.add("Electronics");
                rowData.add(product.getPrice());
                rowData.add(electronics.getBrand() + " , " + electronics.getWarrantyPeriod() + " weeks warranty");

            } else if (product instanceof Clothing) {
                rowData.add("Clothing");
                rowData.add(product.getPrice());
                Clothing clothing = (Clothing) product;
                rowData.add(clothing.getSize() + " , " + clothing.getColor());

            } else {
                // Handle other product types if needed
                rowData.add(""); // Empty column for other types
            }

            if (product.getAvailableItems() < 10) {
                rowData.add("<html><font color='red'>" + product.getProductId() + "</font></html>");
            } else {
                rowData.add(product.getAvailableItems());
            }

            rowData.add(productDetailsToString(product));
            tableModel.addRow(rowData);
            applyRowColor();

        }

    }

    // Method for getting a product by its ID from a list of products
    private Product getProductById(String product_id, List<Product> productList) {
        for (Product product : productList) {
            if (product.getProductId().equals(product_id)) {
                return product;
            }
        }
        return null;
    }

    // Method for applying row color based on available items
    private void applyRowColor() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String product_id = (String) table.getValueAt(row, 0);
                Product product = getProductById(product_id, shoppingManager.getProductList());

                if (product != null && product.getAvailableItems() < 10) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(table.getBackground());
                }
                return c;
            }
        };

        // Set the renderer for each column
        for (int i = 0; i < productTable.getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    // Method for adding a selected product to the shopping cart
    private void addToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            // Add the selected product to the shopping cart
            Product selectedProduct = shoppingManager.getProductList().get(selectedRow);
            shoppingCart.addProduct(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product added to the shopping cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to the shopping cart.");
        }
    }

    // Method for converting product details to a string
    private String productDetailsToString(Product product) {
        StringBuilder details = new StringBuilder();
        details.append("Product ID: ").append(product.getProductId()).append("\n");
        details.append("Product Name: ").append(product.getProductName()).append("\n");
        details.append("Available Items: ").append(product.getAvailableItems()).append("\n");
        details.append("Price: $").append(product.getPrice()).append("\n");

        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            details.append("Type: Electronics\n");
            details.append("Brand: ").append(electronics.getBrand()).append("\n");
            details.append("Warranty Period: ").append(electronics.getWarrantyPeriod()).append(" months\n");
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            details.append("Type: Clothing\n");
            details.append("Size: ").append(clothing.getSize()).append("\n");
            details.append("Color: ").append(clothing.getColor()).append("\n");
        }

        return details.toString();
    }

    // Method for opening the shopping cart GUI
    private void openShoppingCart() {
        ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(shoppingCart);
        shoppingCartGUI.setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShoppingGUI::new);
    }
}
