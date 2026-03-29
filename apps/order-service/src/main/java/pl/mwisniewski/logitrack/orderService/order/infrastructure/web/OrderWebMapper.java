package pl.mwisniewski.logitrack.orderService.order.infrastructure.web;

import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Order;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.web.response.OrderResponse;

import java.util.List;

public class OrderWebMapper {
    public static OrderResponse toResponse(Order order, List<CargoResponse> cargos) {
        return new OrderResponse(
                order.getId(),
                order.getExpectedDeliveryDate(),
                order.getStatus().toString(),
                order.getExpiresAt(),
                order.getRoute().from(),
                order.getRoute().to(),
                order.getRoute().distance().value().doubleValue(),
                order.getRoute().distance().unit().toString(),
                order.getPrice().price(),
                order.getPrice().currency().toString(),
                order.getIssuer(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                cargos
        );
    }

    public static List<OrderResponse> toFindAllResponse(List<Order> orders, CargoLookup cargoLookup) {
        return orders.stream().map(order -> toResponse(order, cargoLookup.findByOrderId(order.getId()))).toList();
    }

    @FunctionalInterface
    public interface CargoLookup {
        List<CargoResponse> findByOrderId(java.util.UUID orderId);
    }
}