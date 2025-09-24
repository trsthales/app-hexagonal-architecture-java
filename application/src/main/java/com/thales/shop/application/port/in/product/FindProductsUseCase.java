package com.thales.shop.application.port.in.product;

import com.thales.shop.model.product.Product;
import java.util.List;

public interface FindProductsUseCase {

    List<Product> findByNameOrDescription(String query);
}