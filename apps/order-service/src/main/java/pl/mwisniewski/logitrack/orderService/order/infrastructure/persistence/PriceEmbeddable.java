package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record PriceEmbeddable(BigDecimal price, String currency) {
}
