package com.thales.shop.adapter.out.persistence.inmemory;

import com.thales.shop.adapter.out.persistence.AbstractProductRepositoryTest;

public class InMemoryProductRepositoryTest extends AbstractProductRepositoryTest<InMemoryProductRepository> {
    @Override
    protected InMemoryProductRepository createProductRepository() {
        return new InMemoryProductRepository();
    }
}
