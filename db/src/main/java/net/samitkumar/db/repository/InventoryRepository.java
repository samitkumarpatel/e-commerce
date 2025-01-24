package net.samitkumar.db.repository;

import net.samitkumar.db.entity.Inventory;
import org.springframework.data.repository.ListCrudRepository;

public interface InventoryRepository extends ListCrudRepository<Inventory, Long> {
}
