package com.thales.shop.application.port.in.cart;

import com.thales.shop.model.customer.CustomerId;

public interface EmptyCartUseCase {
    void emptyCart(CustomerId customerId);
}
