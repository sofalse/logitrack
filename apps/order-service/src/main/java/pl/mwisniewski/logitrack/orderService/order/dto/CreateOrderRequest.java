package pl.mwisniewski.logitrack.orderService.order.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;


public record CreateOrderRequest(
        @NotBlank
        @UUID
        String id
) {}