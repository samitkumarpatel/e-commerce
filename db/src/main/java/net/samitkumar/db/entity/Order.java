package net.samitkumar.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table("orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
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
