package pl.mwisniewski.logitrack.orderService.cargo.domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Cargo {
    private final UUID id;
    private final UUID orderId;
    private final String description;
    private final CargoStatus status;
    private final Dimensions dimensions;
    private final Weight weight;

    public Cargo(UUID id, UUID orderId, String description, CargoStatus status, Dimensions dimensions, Weight weight) {
        if (weight.value().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        this.id = id;
        this.orderId = orderId;
        this.description = description;
        this.status = status;
        this.dimensions = dimensions;
        this.weight = weight;
    }
}
