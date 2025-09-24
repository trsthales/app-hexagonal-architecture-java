package com.thales.shop.adapter.in.rest;

import com.thales.shop.model.customer.CustomerId;
import jakarta.ws.rs.core.Response;

import static com.thales.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

public final class CustomerIdParser {

    private CustomerIdParser() {}

    public static CustomerId parseCustomerId(String string) {
        try {
            return new CustomerId(Integer.parseInt(string));
        } catch (IllegalArgumentException e) {
            throw clientErrorException(Response.Status.BAD_REQUEST, "Invalid 'customerId'");
        }
    }

}
