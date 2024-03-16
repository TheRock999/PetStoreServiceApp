package ServiceImpl;

import Models.Product;
import Services.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    // Utilizamos un Map para almacenar los productos, donde la clave es el ID del producto
    private Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new IllegalArgumentException("No se encontró ningún producto con el ID: " + id);
        }
        return product;
    }

    @Override
    public void createProduct(Product product) {
        // Validar que el producto no sea nulo y que no tenga un ID asignado
        if (product == null || product.getId() != null) {
            throw new IllegalArgumentException("El producto no es válido para crear.");
        }
        product.setId(nextId);
        products.put(nextId, product);
        nextId++;
    }

    @Override
    public void updateProduct(Long id, Product product) {
        // Validar que el producto exista en la base de datos
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("No se encontró ningún producto con el ID: " + id);
        }
        // Validar que el ID del producto coincida con el ID proporcionado
        if (!id.equals(product.getId())) {
            throw new IllegalArgumentException("El ID del producto no coincide con el ID proporcionado.");
        }
        products.put(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        // Validar que el producto exista en la base de datos
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("No se encontró ningún producto con el ID: " + id);
        }
        products.remove(id);
    }
}
