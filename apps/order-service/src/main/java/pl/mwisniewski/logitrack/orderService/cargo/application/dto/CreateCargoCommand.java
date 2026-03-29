package pl.mwisniewski.logitrack.orderService.cargo.application.dto;

import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Dimensions;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Weight;

import java.util.UUID;

public record CreateCargoCommand(UUID orderId, Weight weight, String description, Dimensions dimensions) {
}
