package net.samitkumar.db.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_address")
@Data
@Builder
public class UserAddress {
	@Id
	private Long id;
	private Long users;
	private String address;
	@ReadOnlyProperty
	private LocalDateTime addedAt;
}
