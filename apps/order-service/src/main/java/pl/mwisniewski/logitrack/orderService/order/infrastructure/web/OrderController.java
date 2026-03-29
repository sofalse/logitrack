package pl.mwisniewski.logitrack.orderService.order.infrastructure.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.OrderNotFoundException;
import pl.mwisniewski.logitrack.orderService.order.dto.CreateOrderRequest;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController()
public class OrderController {

//    private final OrderRepository repository;
//
//    public OrderController(OrderRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping(path = "/")
//    public List<Order> findAll() {
//        return this.repository.findAll();
//    }
//
//    @GetMapping(path = "/{id}")
//    public Order findById(@PathVariable UUID id) {
//        return this.repository.findOrderById(id).orElseThrow(() -> new OrderNotFoundException(id.toString()));
//    }
//
//    @PostMapping(path = "/")
//    public Order create(@Valid @RequestBody CreateOrderRequest orderRequest) {
//        Order order = new Order();
//
//        return this.repository.save(order);
//    }
}