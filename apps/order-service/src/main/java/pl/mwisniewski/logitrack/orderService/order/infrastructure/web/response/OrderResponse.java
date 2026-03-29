package pl.mwisniewski.logitrack.orderService.order.infrastructure.web.response;

import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID id, OffsetDateTime expectedDeliveryDate, String status, Instant expiresAt, String from,
                            String to, double distanceValue, String distanceUnit, BigDecimal price, String currency,
                            String issuer,
                            Instant createdAt, Instant updatedAt, List<CargoResponse> cargos) {
}
