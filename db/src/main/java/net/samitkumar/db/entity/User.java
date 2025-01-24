package net.samitkumar.db.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table("users")
@Data
@Builder
public class User {
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
