package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record RouteEmbeddable(String from, String to, @Embedded DistanceEmbeddable distance) {
}
