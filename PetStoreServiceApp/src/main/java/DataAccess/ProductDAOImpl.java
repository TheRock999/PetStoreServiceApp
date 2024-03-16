package DataAccess;

import Exceptions.ProductNotFoundException;
import Models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductDAOImpl implements ProductDAO {
    // Utilizamos ConcurrentHashMap para admitir acceso concurrente a los datos
    private final ConcurrentMap<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Override
    public void createProduct(Product product) {
        validateProduct(product);
        long id = nextId.getAndIncrement();
        product.setId(id);
        products.put(id, product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        if (!products.containsKey(id)) {
            ProductNotFoundException exception = new ProductNotFoundException();
            exception.setMessage("No se encontró ningún producto con el ID: " + id);
            throw exception;
        }
        validateProduct(product);
        product.setId(id);
        products.put(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product removedProduct = products.remove(id);
        if (removedProduct == null) {
            ProductNotFoundException exception = new ProductNotFoundException();
            exception.setMessage("No se encontró ningún producto con el ID: " + id);
            throw exception;
        }
    }

    // Método privado para validar un producto
    private void validateProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }
    }
}
