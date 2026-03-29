package pl.mwisniewski.logitrack.orderService.order.domain.model;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Order {
    private UUID id;
    private OffsetDateTime expectedDeliveryDate;
    private OrderStatus status;
    private Instant expiresAt;
    private Route route;
    private Price price;
    // @TODO: refactor to separate Issuer VO/Domain
    private String issuer;
}
