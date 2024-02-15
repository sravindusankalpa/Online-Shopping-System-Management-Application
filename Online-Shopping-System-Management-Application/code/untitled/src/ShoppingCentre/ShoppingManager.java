package ShoppingCentre;

import java.util.List;

// Interface ShoppingManager
public interface ShoppingManager {
    void addProduct(Product product);

    void deleteProduct(Product product);

    void printProducts();
    void saveProductsToFile();

    void readProductsFromFile();

    List<Product> getProductList();

    List<Product> getElectronicsProducts();

    List<Product> getClothingProducts();
}

