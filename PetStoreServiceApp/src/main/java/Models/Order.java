package Models;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date createdAt;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    // Constructor por defecto
    public Order() {
    }

    // Constructor con todos los parámetros
    public Order(Long id, Date createdAt, Customer customer, List<OrderItem> items, OrderStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.customer = customer;
        this.items = items;
        this.status = status;
    }

    // Getters y Setters para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y Setters para createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getters y Setters para customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getters y Setters para items
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Getters y Setters para status
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
