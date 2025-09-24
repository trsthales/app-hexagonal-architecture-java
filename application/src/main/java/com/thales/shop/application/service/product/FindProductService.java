package com.thales.shop.application.service.product;

import com.thales.shop.application.port.in.product.FindProductsUseCase;
import com.thales.shop.application.port.out.persistence.ProductRepository;
import com.thales.shop.model.product.Product;

import java.util.List;
import java.util.Objects;

public class FindProductService implements FindProductsUseCase {

    private final ProductRepository productRepository;

    public FindProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findByNameOrDescription(String query) {
        Objects.requireNonNull(query, "'query' must not be null");
        if (query.length() < 2) {
            throw new IllegalArgumentException("'query' must be at least two characters long");
        }

        return productRepository.findByNameOrDescription(query);
    }
}
