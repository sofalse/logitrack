package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mwisniewski.logitrack.orderService.cargo.application.CargoService;
import pl.mwisniewski.logitrack.orderService.cargo.application.dto.CreateCargoCommand;
import pl.mwisniewski.logitrack.orderService.cargo.domain.exception.CargoNotFoundException;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Dimensions;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Weight;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.request.CreateCargoRequest;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;
import pl.mwisniewski.logitrack.orderService.common.PageResult;
import pl.mwisniewski.logitrack.orderService.order.application.OrderService;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.OrderNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<CargoResponse> createCargo(@Valid @RequestBody CreateCargoRequest request) {
        orderService.findOrder(request.orderId())
                .orElseThrow(() -> new OrderNotFoundException(request.orderId().toString()));

        CreateCargoCommand command = new CreateCargoCommand(
                request.orderId(),
                new Weight(request.weight(), request.weightUnit()),
                request.description(),
                new Dimensions(request.length(), request.width(), request.height())
        );
        Cargo cargo = cargoService.createCargo(command);

        CargoResponse response = CargoWebMapper.toResponse(cargo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PageResult<CargoResponse>> findAllCargos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResult<Cargo> result = cargoService.findAllCargos(page, size);

        PageResult<CargoResponse> response = new PageResult<>(
                CargoWebMapper.toFindAllResponse(result.content()),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponse> findCargoById(@PathVariable UUID id) {
        Cargo cargo = cargoService.getCargo(id).orElseThrow(() -> new CargoNotFoundException(id.toString()));

        CargoResponse response = CargoWebMapper.toResponse(cargo);

        return ResponseEntity.ok(response);
    }
}
