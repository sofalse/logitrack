package pl.mwisniewski.logitrack.orderService.order.domain.ports;

import pl.mwisniewski.logitrack.orderService.order.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order create(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    void accept(Order order);

    Order update(Order order);

    void deleteById(UUID id);
}
