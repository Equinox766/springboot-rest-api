package org.equinox.apirest.services;

import org.equinox.apirest.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product product);
    Product delete(Long id);
    boolean existsBySku(String sku);
}
