package org.equinox.apirest.repositories;

import org.equinox.apirest.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
}
