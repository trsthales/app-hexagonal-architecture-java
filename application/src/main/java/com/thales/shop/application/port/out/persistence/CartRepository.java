package com.thales.shop.application.port.out.persistence;

import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.customer.CustomerId;

import java.util.Optional;

public interface CartRepository {
    void save(Cart cart);

    Optional<Cart> findByCustomerId(CustomerId customerId);

    void deleteById(CustomerId customerId);
}
