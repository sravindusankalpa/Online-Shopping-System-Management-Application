package ShoppingCentre;

// Subclass Clothing extending Product
public class Clothing extends Product {
    // Additional attributes specific to Electronics
    private String size;//size of clothing
    private String color;//clothing color

    // Constructor for Clothing class
    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    // Getter and setter for size
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Implementation of abstract method
    @Override
    public void displayProductDetails() {
        System.out.println("Product ID: " + getProductId());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Available Items: " + getAvailableItems());
        System.out.println("Price: $" + getPrice());
        System.out.println("Size: " + getSize());
        System.out.println("Color: " + getColor());
    }
}
