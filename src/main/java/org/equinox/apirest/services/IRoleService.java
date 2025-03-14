package org.equinox.apirest.services;

import org.equinox.apirest.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> getAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    Optional<Role> update(Long id, Role role);
    Optional<Role> delete(Long id);
}
