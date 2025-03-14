package org.equinox.apirest.services.Impl;

import org.equinox.apirest.entities.Role;
import org.equinox.apirest.repositories.IRoleRepository;
import org.equinox.apirest.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Optional<Role> update(Long id, Role role) {
        role.setId(id);
        return Optional.of(roleRepository.save(role));
    }

    @Override
    @Transactional
    public Optional<Role> delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        role.ifPresent(roleRepository::delete);
        return role;
    }
}
