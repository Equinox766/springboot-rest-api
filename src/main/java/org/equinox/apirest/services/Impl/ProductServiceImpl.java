package org.equinox.apirest.services.Impl;

import org.equinox.apirest.entities.Product;
import org.equinox.apirest.repositories.IProductRepository;
import org.equinox.apirest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        product.setId(id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product p = productOptional.orElseThrow();
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setSku(product.getSku());
            return Optional.of(productRepository.save(product));
        }
        return productOptional;
    }

    @Override
    @Transactional
    public Product delete(Long id) {
        Optional<Product> product =  productRepository.findById(id);
        product.ifPresent(productRepository::delete);
        return product.get();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}
