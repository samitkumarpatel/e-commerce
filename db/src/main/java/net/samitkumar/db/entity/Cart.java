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

@Table("cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private Long id;
    private Long users; // Reference to the User table
    @ReadOnlyProperty
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "cart") // One-to-many relationship
    private Set<CartItem> items;
}
