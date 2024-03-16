package Models;

public class OrderItem {
    private Long id;
    private Product product;
    private Integer quantity;

    // Constructor por defecto
    public OrderItem() {
    }

    // Constructor con todos los parámetros
    public OrderItem(Long id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    // Getter para el ID
    public Long getId() {
        return id;
    }

    // Setter para el ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para el producto
    public Product getProduct() {
        return product;
    }

    // Setter para el producto
    public void setProduct(Product product) {
        this.product = product;
    }

    // Getter para la cantidad
    public Integer getQuantity() {
        return quantity;
    }

    // Setter para la cantidad
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
