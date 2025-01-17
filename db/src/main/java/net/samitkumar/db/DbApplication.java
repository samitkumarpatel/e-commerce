package net.samitkumar.db;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

}

@Table("users")
@Data
@Builder
class User {
	@Id
	private Long id;
	private String name;
	private String email;
	private String passwordHash;
	@ReadOnlyProperty
	private LocalDateTime createdAt;

	@MappedCollection(idColumn = "users")
	Set<UserAddress> addresses;
}

@Table("user_address")
@Data
@Builder
class UserAddress {
	@Id
	private Long id;
	private Long users;
	private String address;
	@ReadOnlyProperty
	private LocalDateTime addedAt;
}

interface UserRepository extends ListCrudRepository<User, Long>{}

@Table("category")
@Data
@Builder
class Category {
	@Id
	private Long id;
	private String name;
	private String description;
	@ReadOnlyProperty
	private LocalDateTime createdAt;
}

interface CategoryRepository extends ListCrudRepository<Category, Long>{}

@Table("product")
@Data
@Builder
class Product {
	@Id
	private Long id;
	private String name;
	private String description;
	private Double price;
	@ReadOnlyProperty
	private LocalDateTime createdAt;
	private Long category;

}

interface ProductRepository extends ListCrudRepository<Product, Long> {}

@Table("inventory")
@Data
@Builder
class Inventory {
	@Id
	private Long id;
	private Long product;
	private Integer quantity;
	@ReadOnlyProperty
	private LocalDateTime updatedAt;
}

interface InventoryRepository extends ListCrudRepository<Inventory, Long> {}

@Table("cart")
@Data
@Builder
class Cart {
	@Id
	private Long id;
	private Long users; // Reference to the User table
	@ReadOnlyProperty
	private LocalDateTime createdAt;

	@MappedCollection(idColumn = "cart") // One-to-many relationship
	private Set<CartItem> items;
}

@Table("cart_item")
@Data
@Builder
class CartItem {
	@Id
	private Long id;
	private Long cart;
	private Long product;
	private Integer quantity;
	@ReadOnlyProperty
	private LocalDateTime addedAt;
}

interface CartRepository extends ListCrudRepository<Cart, Long> {}

enum OrderStatus {
	PENDING, PROCESSED, DELIVERED
}

@Table("orders")
@Data
@Builder
class Order {
	@Id
	private Long id;
	private Long users; // Reference to the User table
	@Builder.Default
	private OrderStatus status = OrderStatus.PENDING;
	private Double totalPrice;
	@ReadOnlyProperty
	private LocalDateTime createdAt;
	private Long deliveryAddress;
	private String paymentReference;
	@MappedCollection(idColumn = "orders") // One-to-many relationship
	private Set<OrderItem> items;
}

@Table("order_item")
@Data
@Builder
class OrderItem {
	@Id
	private Long id;
	private Long orders;
	private Long product;
	private Integer quantity;
	private Double price;
	@ReadOnlyProperty
	private LocalDateTime addedAt;
}

interface OrderRepository extends ListCrudRepository<Order, Long> {}