package com.thales.shop.adapter.out.persistence.inmemory;

import com.thales.shop.application.port.out.persistence.CartRepository;
import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.customer.CustomerId;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCartRepository implements CartRepository {

    private final Map<CustomerId, Cart> carts = new ConcurrentHashMap<>();

    @Override
    public void save(Cart cart) {
        carts.put(cart.id(), cart);
    }

    @Override
    public Optional<Cart> findByCustomerId(CustomerId customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    @Override
    public void deleteById(CustomerId customerId) {
        carts.remove(customerId);
    }

}
