package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record WeightEmbeddable(@Column(name = "weight_value")BigDecimal weight_value, @Column(name = "weight_unit") String weight_unit) {
}
