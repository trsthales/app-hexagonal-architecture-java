package com.thales.shop.adapter.in.rest.cart;

import com.thales.shop.adapter.in.rest.AddToCartController;
import com.thales.shop.adapter.in.rest.EmptyCartController;
import com.thales.shop.adapter.in.rest.GetCartController;
import com.thales.shop.application.port.in.cart.AddToCartUseCase;
import com.thales.shop.application.port.in.cart.EmptyCartUseCase;
import com.thales.shop.application.port.in.cart.GetCartUseCase;
import com.thales.shop.application.port.in.cart.ProductNotFoundException;
import com.thales.shop.model.cart.Cart;
import com.thales.shop.model.cart.NotEnoughItemsInStockException;
import com.thales.shop.model.customer.CustomerId;
import com.thales.shop.model.product.Product;
import com.thales.shop.model.product.ProductId;
import io.restassured.response.Response;
import jakarta.ws.rs.core.Application;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.thales.shop.adapter.in.rest.HttpTestCommons.TEST_PORT;
import static com.thales.shop.adapter.in.rest.HttpTestCommons.assertThatResponseIsError;
import static com.thales.shop.adapter.in.rest.cart.CartsControllerAssertions.assertThatResponseIsCart;
import static com.thales.shop.model.money.TestMoneyFactory.euros;
import static com.thales.shop.model.product.TestProductFactory.createTestProduct;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartsControllerTest {
    private static final CustomerId TEST_CUSTOMER_ID = new CustomerId(61157);
    private static final Product TEST_PRODUCT_1 = createTestProduct(euros(19, 99));

    private static final AddToCartUseCase addToCartUseCase = mock(AddToCartUseCase.class);
    private static final GetCartUseCase getCartUseCase = mock(GetCartUseCase.class);
    private static final EmptyCartUseCase emptyCartUseCase = mock(EmptyCartUseCase.class);

    private static UndertowJaxrsServer server;

    @BeforeAll
    static void init() {
        server =
                new UndertowJaxrsServer()
                        .setPort(TEST_PORT)
                        .start()
                        .deploy(
                                new Application() {
                                    @Override
                                    public Set<Object> getSingletons() {
                                        return Set.of(
                                                new AddToCartController(addToCartUseCase),
                                                new GetCartController(getCartUseCase),
                                                new EmptyCartController(emptyCartUseCase));
                                    }
                                });
    }

    @AfterAll
    static void stop() {
        server.stop();
    }

    @Test
    void givenSomeTestData_addLineItem_invokesAddToCartUseCaseAndReturnsUpdatedCart()
            throws NotEnoughItemsInStockException, ProductNotFoundException {
        // Arrange
        CustomerId customerId = TEST_CUSTOMER_ID;
        ProductId productId = TEST_PRODUCT_1.id();
        int quantity = 5;

        Cart cart = new Cart(customerId);
        cart.addProduct(TEST_PRODUCT_1, quantity);

        when(addToCartUseCase.addToCart(customerId, productId, quantity)).thenReturn(cart);

        // Act
        Response response =
                given()
                        .port(TEST_PORT)
                        .queryParam("productId", productId.value())
                        .queryParam("quantity", quantity)
                        .post("/carts/" + customerId.value() + "/line-items")
                        .then()
                        .extract()
                        .response();

        // Assert
        assertThatResponseIsCart(response, cart);
    }
}
