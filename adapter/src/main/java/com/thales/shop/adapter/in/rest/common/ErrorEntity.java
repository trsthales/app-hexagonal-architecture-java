package com.thales.shop.adapter.in.rest.common;

public record ErrorEntity(int httpStatus, String errorMessage) {
}
