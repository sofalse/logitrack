package pl.mwisniewski.logitrack.orderService.order.domain.model;

import java.math.BigDecimal;

public record Price(BigDecimal price, Currency currency) {

    public Price(double price, String currency) {
        this(BigDecimal.valueOf(price), Currency.valueOf(currency));
    }

    public enum Currency { USD, GBP, PLN, EUR }
}
