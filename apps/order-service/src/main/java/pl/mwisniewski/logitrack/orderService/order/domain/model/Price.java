package pl.mwisniewski.logitrack.orderService.order.domain.model;

import java.math.BigDecimal;

public record Price(BigDecimal price, Currency currency) {
    // @TODO: Refactor currency to VO
    public enum Currency { USD, GBP, PLN, EUR }
}
