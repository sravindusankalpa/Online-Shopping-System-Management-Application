package ShoppingCentre;

import java.util.Objects;

// Abstract class Product
public abstract class Product {
    // Attributes of a Product
    private String productId;// Unique identifier for the product
    private String productName;// Name of the product
    private int availableItems;// Number of available items in stock
    private double price;// Price of each unit of the product

    // Constructor for Product class
    public Product(String productId, String productName, int availableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayProductDetails();

    // Getters and setters for encapsulation
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {

        this.productId = productId;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public int getAvailableItems() {

        return availableItems;
    }

    public void setAvailableItems(int availableItems) {

        this.availableItems = availableItems;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(getProductId(), product.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }


}
