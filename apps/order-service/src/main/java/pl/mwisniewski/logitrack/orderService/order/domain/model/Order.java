package pl.mwisniewski.logitrack.orderService.order.domain.model;

import lombok.Getter;
import pl.mwisniewski.logitrack.orderService.common.Default;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.DomainException;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class Order {
    private final UUID id;
    private final OffsetDateTime expectedDeliveryDate;
    private OrderStatus status;
    private final Instant expiresAt;
    private final Route route;
    private final Price price;
    // @TODO: refactor to separate Issuer VO/Domain
    private final String issuer;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Order(UUID id, OffsetDateTime expectedDeliveryDate, OrderStatus status, Instant expiresAt, Route route, Price price, String issuer) {
        this(id, expectedDeliveryDate, status, expiresAt, route, price, issuer, null, null);
    }

    @Default
    public Order(UUID id, OffsetDateTime expectedDeliveryDate, OrderStatus status, Instant expiresAt, Route route, Price price, String issuer, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.status = status;
        this.expiresAt = expiresAt;
        this.route = route;
        this.price = price;
        this.issuer = issuer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void accept() {
        if (this.status != OrderStatus.OPEN) {
            throw new DomainException("Can only approve orders with status OPEN");
        }

        this.status = OrderStatus.IN_PROGRESS;
    }
}
