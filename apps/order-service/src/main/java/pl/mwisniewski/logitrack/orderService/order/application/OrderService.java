package pl.mwisniewski.logitrack.orderService.order.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mwisniewski.logitrack.orderService.common.PageResult;
import pl.mwisniewski.logitrack.orderService.order.application.dto.CreateOrderCommand;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.OrderNotFoundException;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Order;
import pl.mwisniewski.logitrack.orderService.order.domain.ports.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(CreateOrderCommand command) {
        Order order = new Order(UUID.randomUUID(), command.expectedDeliveryDate(), command.status(), command.expiresAt(), command.route(), command.price(), command.issuer());

        return orderRepository.save(order);
    }

    @Transactional
    public Optional<Order> findOrder(UUID id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public PageResult<Order> findAllOrders(int page, int size) {
        return orderRepository.findAll(page, size);
    }

    @Transactional
    public void acceptOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
        order.accept();

        orderRepository.save(order);
    }

    @Transactional
    public void removeOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
