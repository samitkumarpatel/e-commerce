package net.samitkumar.db.repository;

import net.samitkumar.db.entity.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Long> {
}
