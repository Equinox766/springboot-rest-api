package org.equinox.apirest.repositories;

import org.equinox.apirest.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
