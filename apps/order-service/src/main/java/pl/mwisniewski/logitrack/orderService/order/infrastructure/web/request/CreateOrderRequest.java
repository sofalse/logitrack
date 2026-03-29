package pl.mwisniewski.logitrack.orderService.order.infrastructure.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateOrderRequest(@NotNull OffsetDateTime expectedDeliveryDate, @NotBlank String issuer,
                                 @NotNull Instant expiresAt,
                                 @NotBlank String from, @NotBlank String to, @NotNull double distance,
                                 @NotBlank String distanceUnit, @NotNull double price, @ValidCurrency String currency) {
}
