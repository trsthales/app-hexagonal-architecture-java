package com.thales.shop.application.port.in.cart;

import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.cart.NotEnoughItemsInStockException;
import com.thales.shop.model.customer.CustomerId;
import com.thales.shop.model.product.ProductId;

public interface AddToCartUseCase {
    Cart addToCart(CustomerId customerId, ProductId productId, int quantity)
            throws ProductNotFoundException, NotEnoughItemsInStockException;
}
