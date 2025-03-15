package org.equinox.apirest.services;

import org.equinox.apirest.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAll();
    Optional<User> findById(Long id);
    User save(User user);
    Optional<User> update(Long id, User user);
    Optional<User> delete(Long id);
    boolean existsUsername(String username);
}
