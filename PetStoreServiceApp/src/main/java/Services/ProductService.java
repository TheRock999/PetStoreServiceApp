package Services;

import Models.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
