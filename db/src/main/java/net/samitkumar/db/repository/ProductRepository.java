package net.samitkumar.db.repository;

import net.samitkumar.db.entity.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
