package net.samitkumar.db.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("product")
@Data
@Builder
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
    @ReadOnlyProperty
    private LocalDateTime createdAt;
    private Long category;
    @MappedCollection(idColumn = "product")
    private Inventory inventory;

}
