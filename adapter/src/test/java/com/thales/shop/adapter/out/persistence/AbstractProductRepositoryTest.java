package com.thales.shop.adapter.out.persistence;

import com.thales.shop.application.port.out.persistence.ProductRepository;
import com.thales.shop.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractProductRepositoryTest <T extends ProductRepository> {
    private T productRepository;

    @BeforeEach
    void initRepository() {
        productRepository = createProductRepository();
    }

    protected abstract T createProductRepository();

    @Test
    void givenTestProducts_findByNameOrDescription_returnsMatchingProducts() {
        String query = "monitor";

        List<Product> products = productRepository.findByNameOrDescription(query);

        assertThat(products)
                .containsExactlyInAnyOrder(
                        DemoProducts.COMPUTER_MONITOR, DemoProducts.MONITOR_DESK_MOUNT);
    }
}
