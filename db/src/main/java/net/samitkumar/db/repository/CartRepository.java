package net.samitkumar.db.repository;

import net.samitkumar.db.entity.Cart;
import org.springframework.data.repository.ListCrudRepository;

public interface CartRepository extends ListCrudRepository<Cart, Long> {
}
