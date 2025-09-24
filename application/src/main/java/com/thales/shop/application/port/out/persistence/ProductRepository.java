package com.thales.shop.application.port.out.persistence;

import com.thales.shop.model.product.Product;
import com.thales.shop.model.product.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findById(ProductId productId);
    List<Product> findByNameOrDescription(String query);
}
