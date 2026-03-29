package pl.mwisniewski.logitrack.orderService.cargo.application.dto;

import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Dimensions;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Weight;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Route;

public record CreateCargoCommand(Route route, Weight weight, String description, Dimensions dimensions) {
}
