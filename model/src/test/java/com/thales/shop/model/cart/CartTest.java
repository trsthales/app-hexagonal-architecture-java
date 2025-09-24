package com.thales.shop.model.cart;


import com.thales.shop.model.product.Product;
import com.thales.shop.model.product.TestProductFactory;
import org.junit.jupiter.api.Test;

import static com.thales.shop.model.money.TestMoneyFactory.euros;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CartTest {

    @Test
    void givenEmptyCart_addTwoProducts_numberOfItemsAndSubTotalIsCalculatedCorrectly()
            throws NotEnoughItemsInStockException {
        Cart cart = TestCartFactory.emptyCartForRandomCustomer();

        Product product1 = TestProductFactory.createTestProduct(euros(12, 99));
        Product product2 = TestProductFactory.createTestProduct(euros(5, 97));

        cart.addProduct(product1, 3);
        cart.addProduct(product2, 5);

        assertThat(cart.numberOfItems()).isEqualTo(8);
        assertThat(cart.subTotal()).isEqualTo(euros(68, 82));
    }

}
