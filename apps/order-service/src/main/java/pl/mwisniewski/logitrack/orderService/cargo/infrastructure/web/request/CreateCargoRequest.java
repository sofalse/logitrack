package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCargoRequest(@NotNull UUID orderId, @NotBlank String description, @NotNull double width,
                                 @NotNull double length,
                                 @NotNull double height, @NotNull
                                 int weight, @NotNull String weightUnit) {
}
