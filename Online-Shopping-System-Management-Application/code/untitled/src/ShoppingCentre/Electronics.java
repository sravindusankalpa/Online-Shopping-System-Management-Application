package ShoppingCentre;

// Subclass Electronics extending Product
public class Electronics extends Product  {
    // Additional attributes specific to Electronics
    private String brand;// Brand of the electronic product
    private int warrantyPeriodMonths; // Warranty period in months

    // Constructor for Electronics class
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriodMonths) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    // Getter and setter for brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter and setter for warrantyPeriodMonths
    public int getWarrantyPeriod() {
        return warrantyPeriodMonths;
    }

    public void setWarrantyPeriod(int warrantyPeriodMonths) {
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    // Implementation of abstract method
    @Override
    public void displayProductDetails() {
        System.out.println("Product ID: " + getProductId());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Available Items: " + getAvailableItems());
        System.out.println("Price: $" + getPrice());
        System.out.println("Brand: " + getBrand());
        System.out.println("Warranty Period: " + getWarrantyPeriod() + " months");
    }

}
