package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Dimensions;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Weight;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Route;

import java.util.UUID;

public record CreateCargoRequest(UUID orderId, @NotBlank String description, @NotNull Dimensions dimensions, @NotNull
                                 Weight weight, Route route) {
}
