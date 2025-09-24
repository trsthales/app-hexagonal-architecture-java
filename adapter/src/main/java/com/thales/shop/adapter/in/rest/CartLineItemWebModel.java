package com.thales.shop.adapter.in.rest;

import com.thales.shop.model.cart.CartLineItem;
import com.thales.shop.model.money.Money;
import com.thales.shop.model.product.Product;

public record CartLineItemWebModel(String productId, String productName, Money price, int quantity) {

    public static CartLineItemWebModel fromDomainModel(CartLineItem lineItem) {
        Product product = lineItem.product();
        return new CartLineItemWebModel(
                product.id().value(), product.name(), product.price(), lineItem.quantity());
    }

}
