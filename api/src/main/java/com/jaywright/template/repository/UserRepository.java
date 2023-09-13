package com.jaywright.template.repository;

import com.jaywright.template.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {
  Optional<User> findByUsername(String username);

  User getByUsername(String username);

  Boolean existsByUsername(String username);
}
