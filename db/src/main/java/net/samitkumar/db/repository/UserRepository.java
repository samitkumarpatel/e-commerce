package net.samitkumar.db.repository;

import net.samitkumar.db.entity.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
}
