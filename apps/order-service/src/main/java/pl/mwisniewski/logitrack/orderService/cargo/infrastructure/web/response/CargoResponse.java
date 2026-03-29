package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CargoResponse(UUID id, UUID orderId, String description, double width, double length, double height, BigDecimal weight, String weightUnit, String status, Instant createdAt, Instant updatedAt) {
}
