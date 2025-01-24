package net.samitkumar.db.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("inventory")
@Data
@Builder
public class Inventory {
    @Id
    private Long id;
    private Long product;
    private Integer quantity;
    @ReadOnlyProperty
    private LocalDateTime updatedAt;
}
