package ShoppingCentre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingCart {
    private List<Product> productList;

    private boolean isUserSignedUp;


    public ShoppingCart(boolean isUserSignedUp) {
        this.productList = new ArrayList<>();
        this.isUserSignedUp = isUserSignedUp;
    }

    public ShoppingCart() {   // Constructor initializes an empty list of products
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) { // Method to add a product to the shopping cart
        productList.add(product);
    }

    // Method to get a map of product quantities in the shopping cart
    public Map<Object, Integer> getProductQuantity() {
        Map<Object, Integer> productQuantity = new HashMap<>();
        for (Product product : productList) {
            productQuantity.put(product, productQuantity.getOrDefault(product, 0) + 1);
        }
        return productQuantity;
    }

    // Method to calculate the total price of products in the shopping cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : productList) {
            total += product.getPrice();
        }
        return total;
    }

    // Method to calculate a 10% discount on the total price
    // Method to calculate a 10% discount on the total price
    public double calculateDiscount10() {
        if (!productList.isEmpty()) {
            return calculateTotal() * 0.1;
        }
        return 0;
    }

    // Method to calculate a 20% discount if the quantity of any product is 3 or more
    public double calculateDiscount20() {
        Map<Object, Integer> productQuantity = getProductQuantity();
        for (int count : productQuantity.values()) {
            if (count >= 3) {
                return calculateTotal() * 0.2;
            }
        }
        return 0;
    }

    // Method to calculate the final total after applying discounts
    public double calculateFinalTotal() {
        double total = calculateTotal();
        double discount10 = calculateDiscount10();
        double discount20 = calculateDiscount20();
        return total - discount10 - discount20;
    }


}
