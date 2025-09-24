package com.thales.shop.bootstrap;

import com.thales.shop.adapter.in.rest.AddToCartController;
import com.thales.shop.adapter.in.rest.EmptyCartController;
import com.thales.shop.adapter.in.rest.GetCartController;
import com.thales.shop.adapter.in.rest.product.FindProductsController;
import com.thales.shop.adapter.out.persistence.inmemory.InMemoryCartRepository;
import com.thales.shop.adapter.out.persistence.inmemory.InMemoryProductRepository;
import com.thales.shop.application.port.in.cart.AddToCartUseCase;
import com.thales.shop.application.port.in.cart.EmptyCartUseCase;
import com.thales.shop.application.port.in.cart.GetCartUseCase;
import com.thales.shop.application.port.in.product.FindProductsUseCase;
import com.thales.shop.application.port.out.persistence.CartRepository;
import com.thales.shop.application.port.out.persistence.ProductRepository;
import com.thales.shop.application.service.cart.AddToCartService;
import com.thales.shop.application.service.cart.EmptyCartService;
import com.thales.shop.application.service.cart.GetCartService;
import com.thales.shop.application.service.product.FindProductService;
import jakarta.ws.rs.core.Application;

import java.util.Set;

public class RestEasyUndertowShopApplication extends Application {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Override
    public Set<Object> getSingletons() {
        initPersistenceAdapters();

        return Set.of(
                addToCartController(),
                getCartController(),
                emptyCartController(),
                findProductsController());
    }

    private void initPersistenceAdapters() {
        cartRepository = new InMemoryCartRepository();
        productRepository = new InMemoryProductRepository();
    }

    private AddToCartController addToCartController() {
        AddToCartUseCase addToCartUseCase =
                new AddToCartService(cartRepository, productRepository);
        return new AddToCartController(addToCartUseCase);
    }

    private GetCartController getCartController() {
        GetCartUseCase getCartUseCase = new GetCartService(cartRepository);
        return new GetCartController(getCartUseCase);
    }

    private EmptyCartController emptyCartController() {
        EmptyCartUseCase emptyCartUseCase = new EmptyCartService(cartRepository);
        return new EmptyCartController(emptyCartUseCase);
    }

    private FindProductsController findProductsController() {
        FindProductsUseCase findProductsUseCase = new FindProductService(productRepository);
        return new FindProductsController(findProductsUseCase);
    }

}
