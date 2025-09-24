package com.thales.shop.adapter.in.rest.product;

import com.thales.shop.model.money.Money;
import com.thales.shop.model.product.Product;

public record ProductInListWebModel(String id, String name, Money price, int itemsInStock) {
    public static ProductInListWebModel fromDomainModel(Product product) {
        return new ProductInListWebModel(
                product.id().value(), product.name(), product.price(), product.itemsInStock());
    }
}
