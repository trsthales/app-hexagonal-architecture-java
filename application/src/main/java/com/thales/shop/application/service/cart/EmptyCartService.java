package com.thales.shop.application.service.cart;

import com.thales.shop.application.port.in.cart.EmptyCartUseCase;
import com.thales.shop.application.port.out.persistence.CartRepository;
import com.thales.shop.model.customer.CustomerId;

import java.util.Objects;

public class EmptyCartService implements EmptyCartUseCase {
    private final CartRepository cartRepository;

    public EmptyCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void emptyCart(CustomerId customerId) {
        Objects.requireNonNull(customerId, "'customerId' must not be null");

        cartRepository.deleteById(customerId);
    }
}
