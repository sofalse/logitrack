package pl.mwisniewski.logitrack.orderService.order.application.dto;

import pl.mwisniewski.logitrack.orderService.order.domain.model.OrderStatus;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Price;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Route;

import java.time.Instant;
import java.time.OffsetDateTime;

public record CreateOrderCommand(OffsetDateTime expectedDeliveryDate, OrderStatus status, Instant expiresAt,
                                 Route route, Price price, String issuer) {
}
