package net.samitkumar.db.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("category")
@Data
@Builder
public class Category {
    @Id
    private Long id;
    private String name;
    private String description;
    @ReadOnlyProperty
    private LocalDateTime createdAt;
}
