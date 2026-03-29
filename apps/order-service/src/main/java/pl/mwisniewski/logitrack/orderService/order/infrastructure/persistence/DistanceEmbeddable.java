package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record DistanceEmbeddable(@Column(name = "distance_value")BigDecimal distance_value, @Column(name = "distance_unit") String distance_unit) {
}
