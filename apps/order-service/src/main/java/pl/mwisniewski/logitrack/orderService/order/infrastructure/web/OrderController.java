package pl.mwisniewski.logitrack.orderService.order.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mwisniewski.logitrack.orderService.cargo.application.CargoService;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.CargoWebMapper;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;
import pl.mwisniewski.logitrack.orderService.common.PageResult;
import pl.mwisniewski.logitrack.orderService.order.application.OrderService;
import pl.mwisniewski.logitrack.orderService.order.application.dto.CreateOrderCommand;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.OrderNotFoundException;
import pl.mwisniewski.logitrack.orderService.order.domain.model.*;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.web.request.CreateOrderRequest;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.web.response.OrderResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CargoService cargoService;

    @GetMapping
    public PageResult<OrderResponse> findAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResult<Order> result = orderService.findAllOrders(page, size);

        return new PageResult<>(
                OrderWebMapper.toFindAllResponse(result.content(), orderId ->
                        CargoWebMapper.toFindAllResponse(cargoService.findCargosByOrderId(orderId))),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );
    }

    @GetMapping("/{id}")
    public OrderResponse findOrderById(@PathVariable UUID id) {
        Order order = orderService.findOrder(id).orElseThrow(() -> new OrderNotFoundException(id.toString()));
        List<CargoResponse> cargos = CargoWebMapper.toFindAllResponse(cargoService.findCargosByOrderId(id));

        return OrderWebMapper.toResponse(order, cargos);
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderCommand command = new CreateOrderCommand(
                createOrderRequest.expectedDeliveryDate(),
                OrderStatus.OPEN,
                createOrderRequest.expiresAt(),
                new Route(
                        createOrderRequest.from(),
                        createOrderRequest.to(),
                        new Distance(
                                createOrderRequest.distance(),
                                createOrderRequest.distanceUnit()
                        )
                ),
                new Price(
                        createOrderRequest.price(),
                        createOrderRequest.currency()
                ),
                createOrderRequest.issuer());

        Order order = orderService.createOrder(command);

        return OrderWebMapper.toResponse(order, List.of());
    }

    @PatchMapping("/{id}/accept")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptOrder(@PathVariable UUID id) {
        orderService.acceptOrder(id);
    }
}