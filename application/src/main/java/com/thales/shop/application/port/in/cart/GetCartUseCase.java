package com.thales.shop.application.port.in.cart;

import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.customer.CustomerId;

public interface GetCartUseCase {
    Cart getCart(CustomerId customerId);
}
