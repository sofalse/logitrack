package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import jakarta.persistence.Embeddable;

@Embeddable
public record DimensionsEmbeddable(double length, double width, double height) {
}
