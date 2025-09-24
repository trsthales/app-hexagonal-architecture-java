package com.thales.shop.application.service.cart;

import com.thales.shop.application.port.in.cart.GetCartUseCase;
import com.thales.shop.application.port.out.persistence.CartRepository;
import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.customer.CustomerId;

import java.util.Objects;

public class GetCartService implements GetCartUseCase {
    private final CartRepository cartRepository;

    public GetCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart(CustomerId customerId) {
        Objects.requireNonNull(customerId, "'customerId' must not be null");

        return cartRepository
                .findByCustomerId(customerId)
                .orElseGet(() -> new Cart(customerId));
    }
}
