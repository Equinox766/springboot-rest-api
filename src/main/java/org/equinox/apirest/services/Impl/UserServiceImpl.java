package org.equinox.apirest.services.Impl;

import org.equinox.apirest.entities.Product;
import org.equinox.apirest.entities.Role;
import org.equinox.apirest.entities.User;
import org.equinox.apirest.repositories.IRoleRepository;
import org.equinox.apirest.repositories.IUserRepository;
import org.equinox.apirest.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
        roleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, User user) {
        user.setId(id);
        return Optional.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public Optional<User> delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user;
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
