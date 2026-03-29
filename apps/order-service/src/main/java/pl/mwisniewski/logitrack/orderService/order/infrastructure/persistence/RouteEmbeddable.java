package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record RouteEmbeddable(@Column(name = "\"from\"") String from, @Column(name = "\"to\"") String to,
                               @Embedded DistanceEmbeddable distance) {
}
