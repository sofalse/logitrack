package pl.mwisniewski.logitrack.orderService.order.domain.ports;

import pl.mwisniewski.logitrack.orderService.common.PageResult;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findById(UUID id);

    List<Order> findAll();

    PageResult<Order> findAll(int page, int size);

    Order save(Order order);

    void deleteById(UUID id);
}
