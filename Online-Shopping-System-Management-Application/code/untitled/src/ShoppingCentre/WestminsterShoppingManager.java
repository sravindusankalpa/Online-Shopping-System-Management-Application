package ShoppingCentre;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> productList ;
    private Scanner scanner;


    // Constructor
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Implement methods from the ShoppingManager interface
    public void printmenu(){
        // Display the main menu options to the user
        System.out.println("\n******Shopping System Management Menu******");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print all products");
        System.out.println("4. Save products");
        System.out.println("5. Login User");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");//user to enter their choice
    }
    public void displayConsoleMenu() {
        int choice;
        do {
            printmenu();// Display the menu options to the user

            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt(); // If the input is an integer

                    // check if the input is a valid menu option
                    if (choice >= 0 && choice <= 5) {
                        scanner.nextLine(); // Consume the newline character
                        break;
                    } else {
                        // If the input is not a valid menu option, display an error message
                        System.out.println("Invalid choice. Please enter a valid option (0-5).");
                        System.out.print("Enter your choice: ");//user to enter their choice
                    }
                } else {
                    // If the input is not an integer, display an error message
                    System.out.println("Invalid input. Please enter a valid integer.");
                    System.out.print("Enter your choice: ");//user to enter their choice


                }
                scanner.nextLine(); // Consume the invalid input
            }

            // Process the user's choice based on the switch statement
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    printProducts();
                    break;
                case 4:
                    saveProductsToFile();
                    break;
                case 5:
                    userLogin();
                    break;
                case 0:
                    System.out.println("Exiting the shopping system management menu.");
                    break;
            }
        } while (choice != 0);// Continue the loop until the user chooses to exit
    }
    @Override
    public void addProduct(Product product) {
        // Check if the productList has reached its maximum capacity (50 products)
        if (productList.size() < 50) {
            productList.add(product);// If there is space available, add the new product to the productList
            System.out.println("Product added successfully.");
        } else {
            // If the productList is at its maximum capacity, inform the user that no more products can be added
            System.out.println("Maximum number of products reached. Cannot add more.");
        }
    }
    private void addNewProduct() {
        // Display the product type
        System.out.println("******Choose product type******");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        // Initialize the variable to store the chosen product type
        int productType;
        // Input validation loop
        while (true) {
            // Prompt the user to enter their choice
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                // Check if the input is an integer
                productType = scanner.nextInt();
                if (productType == 1 || productType == 2) {
                    // Valid input, break out of the loop
                    break;
                } else {
                    // If the input is not a valid choice, display an error message
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                // If the input is not an integer, display an error message
                System.out.println("Invalid input. Please enter a valid integer (1 or 2).");

            }
            scanner.nextLine(); // Consume the invalid input
        }
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter product ID: ");
        String productID = scanner.nextLine();// Read the entered product ID from the user

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();// Read the entered product name from the user

        // Initialize the variable to store the number of available items
        int availableItems;
        // Input validation loop for available items
        while (true) {
            System.out.print("Enter available items: ");
            if (scanner.hasNextInt()) {
                // Check if the input is an integer
                availableItems = scanner.nextInt();
                if (availableItems >= 0) {
                    // Valid input, break out of the loop
                    break;
                } else {
                    // If the input is not a non-negative number, display an error message
                    System.out.println("Invalid input. Please enter a non-negative number.");

                }
            } else {
                // If the input is not an integer, display an error message
                System.out.println("Invalid input. Please enter a valid number for available items.");

            }
            scanner.nextLine(); // Consume the invalid input
        }
        scanner.nextLine();

        // Initialize the variable to store the number of price
        double price;
        // Input validation loop for price
        while (true) {
            System.out.print("Enter price: ");
            if (scanner.hasNextDouble()) {
                // Check if the input is a double
                price = scanner.nextDouble();
                if (price >= 0) {
                    // Valid input, break out of the loop
                    break;
                } else {
                    // If the input is not a non-negative number, display an error message
                    System.out.println("Invalid input. Please enter a valid price.");
                }
            } else {
                // If the input is not an integer, display an error message
                System.out.println("Invalid input. Please enter a valid  price.");
            }
            scanner.nextLine(); // Consume the newline character
        }
        scanner.nextLine(); // Consume the newline character
        if (productType == 1) {
            // Add electronics brand
            System.out.print("Enter brand: ");
            // Read the entered brand from the user
            String brand = scanner.nextLine();

            // Initialize the variable to store the warranty period
            int warrantyPeriod;
            while (true) {
                System.out.print("Enter warranty period (in weeks): ");
                if (scanner.hasNextInt()) {
                    // Check if the input is an integer
                    warrantyPeriod = scanner.nextInt();
                    if (warrantyPeriod >= 0) {
                        // Valid input, break out of the loop
                        break;
                    } else {
                        // If the input is a non-negative integer, break out of the loop
                        System.out.println("Invalid input. Please enter a non-negative integer for warranty period.");

                    }
                } else {
                    // If the input is not a non-negative integer, display an error message
                    System.out.println("Invalid input. Please enter a valid integer for warranty period.");

                }
                scanner.nextLine(); // Consume the invalid input
            }

            scanner.nextLine(); // Consume the newline character

            // Create an Electronics object and add it to the product list
            Electronics electronics = new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod);
            addProduct(electronics);
        } else if (productType == 2) {
            // Add clothing size
            System.out.print("Enter size: ");
            // Read the entered size from the user
            String size = scanner.nextLine();

            System.out.print("Enter color: ");
            String color = scanner.nextLine();

            // Create a Clothing object and add it to the product list
            Clothing clothing = new Clothing(productID, productName, availableItems, price, size, color);
            addProduct(clothing);
        } else {
            // Display an error message for an invalid product type choice
            System.out.println("Invalid product type choice.");
        }
    }
    // Method to handle the deletion of a product
    private void deleteProduct() {
        // Prompt user for the product ID to delete
        System.out.print("Enter the product ID to delete: ");
        String productIDToDelete = scanner.nextLine();

        // Find the product with the given ID
        Product deletedProduct = findProductById(productIDToDelete);

        // Delete the product if found
        if (deletedProduct != null) {

            deleteProduct(deletedProduct);

            // Display information about the deleted product
            System.out.println("Deleted Product Information:");
            deletedProduct.displayProductDetails();

            // Display the total number of remaining products in the system
            System.out.println("Total number of products left: " + productList.size());
        } else {
            // Inform the user that the product with the specified ID was not found
            System.out.println("Product with ID " + productIDToDelete + " not found.");
        }
    }

    // Method to find a product by its ID
    private Product findProductById(String productID) {
        // Iterate through the product list to find the matching product
        for (Product product : productList) {
            if (product.getProductId().equals(productID)) {
                return product; // Return the found product
            }
        }
        return null; // Return null if product with the specified ID is not found
    }

    // Method to delete a product from the system
    @Override
    public void deleteProduct(Product product) {
        // Remove the specified product from the product list
        productList.remove(product);
        // Inform the user that the product has been deleted successfully
        System.out.println("Product deleted successfully.");
    }

    public void printProducts() {
        // Bubble sort algorithm for sorting products alphabetically based on product ID
        int n = productList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Retrieve product IDs for comparison
                String productID1 = productList.get(j).getProductId();
                String productID2 = productList.get(j + 1).getProductId();

                // Compare product IDs and swap
                if (productID1.compareTo(productID2) > 0) {
                    // Swap products
                    Product temp = productList.get(j);
                    productList.set(j, productList.get(j + 1));
                    productList.set(j + 1, temp);
                }
            }
        }

        // Display the sorted list of products
        System.out.println("******List of Products******");
        for (Product product : productList) {
            // Display common product details
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Available Items: " + product.getAvailableItems());
            System.out.println("Price: " + product.getPrice());

            // Check if the product is electronics or clothing
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                // Display Electronics-specific details
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " months");
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                // Display Clothing-specific details
                System.out.println("Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            }

            System.out.println("-----------------------------------------------------");
        }
    }

    public void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            // Iterate through the product list and write details to the file
            for (Product product : productList) {
                // Write common product details
                writer.write(product.getProductId() + ",");
                writer.write(product.getProductName() + ",");
                writer.write(product.getAvailableItems() + ",");
                writer.write(product.getPrice() + ",");

                // Check if the product is electronics or clothing
                if (product instanceof Electronics) {
                    // Write Electronics-specific details
                    writer.write(((Electronics) product).getBrand() + ",");
                    writer.write(((Electronics) product).getWarrantyPeriod() + ",");
                    writer.write("Electronics");
                } else if (product instanceof Clothing) {
                    // Write Clothing-specific details
                    writer.write(((Clothing) product).getSize() + ",");
                    writer.write(((Clothing) product).getColor() + ",");
                    writer.write("Clothing");
                }
                // Move to the next line for the next product
                writer.newLine();
            }
            System.out.println("Products saved to file successfully!");
        } catch (IOException e) {
            // Report any IOException during the file-saving process
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Method to read products from a file
    public void readProductsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using commas as separators
                String[] parts = line.split(",");

                // Ensure the minimum required parts are available
                if (parts.length < 5) {
                    // If the line doesn't have enough parts, print an error message and skip to the next line
                    System.out.println("Invalid line in the file: " + line);
                    continue;
                }
                // Extract common product information from the parts
                String productType = parts[6];
                String productId = parts[0];
                String productName = parts[1];
                int availableItems = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);

                // Check the product type to determine the specific product details
                if ("Electronics".equals(productType)) {
                    // Ensure there are enough parts for Electronics
                    if (parts.length < 7) {
                        // If the line doesn't have enough parts for Electronics, print an error message and skip to the next line
                        System.out.println("Invalid Electronics line in the file: " + line);
                        continue;
                    }
                    // Extract Electronics-specific details
                    String brand = parts[4];
                    int warrantyPeriod = Integer.parseInt(parts[5]);
                    Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                    productList.add(electronics);
                } else if ("Clothing".equals(productType)) {
                    // Ensure there are enough parts for Clothing
                    if (parts.length < 7) {
                        // If the line doesn't have enough parts for Clothing, print an error message and skip to the next line
                        System.out.println("Invalid Clothing line in the file: " + line);
                        continue;
                    }
                    // Extract Clothing-specific details
                    String size = parts[4];
                    String color = parts[5];
                    // Create a Clothing object and add it to the productList
                    Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
                    productList.add(clothing);
                } else {
                    // If the product type is neither Electronics nor Clothing, print an error message
                    System.out.println("Unknown product type in the file: " + productType);
                }
            }

            System.out.println("Products read from file successfully!");
        } catch (IOException | NumberFormatException e) {
            // Handle any IOException or NumberFormatException that may occur during file reading
            System.out.println("Error reading products from file: " + e.getMessage());
        }
    }

    @Override
    //Get the list of all products.
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    //Get the list of electronics products from the product list.
    public List<Product> getElectronicsProducts() {
        List<Product> electronicsProducts = new ArrayList<>();

        // Iterate through the product list and add electronics products to the new list
        for (Product product : productList) {
            if (product instanceof Electronics) {
                electronicsProducts.add(product);
            }
        }

        return electronicsProducts;
    }

    @Override
    //Get the list of clothing products from the product list.
    public List<Product> getClothingProducts() {
        List<Product> clothingProducts = new ArrayList<>();

        // Iterate through the product list and add clothing products to the new list
        for (Product product : productList) {
            if (product instanceof Clothing) {
                clothingProducts.add(product);
            }
        }

        return clothingProducts;
    }
    private void userLogin() {
        // Create an instance of UserLoginGUI
        UserLoginGUI userLoginGUI = new UserLoginGUI();
        // Show the user login GUI
        userLoginGUI.setVisible(true);
    }

    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.readProductsFromFile(); // Read products from file at the start of the application
        shoppingManager.displayConsoleMenu();//Display Menu
        shoppingManager.saveProductsToFile(); // Save products to file when the application exits
    }
}
