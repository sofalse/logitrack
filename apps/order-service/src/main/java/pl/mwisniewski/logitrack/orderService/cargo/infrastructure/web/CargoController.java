package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mwisniewski.logitrack.orderService.cargo.application.CargoService;
import pl.mwisniewski.logitrack.orderService.cargo.application.dto.CreateCargoCommand;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.request.CreateCargoRequest;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoResponse> createCargo(@Valid @RequestBody CreateCargoRequest request) {
        CreateCargoCommand command = new CreateCargoCommand(
                request.route(),
                request.weight(),
                request.description(),

                request.dimensions()
        );
        Cargo cargo = cargoService.createCargo(command);

        CargoResponse response = CargoWebMapper.toResponse(cargo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CargoResponse>> findAllCargos() {
        List<Cargo> cargos = cargoService.findAllCargos();

        List<CargoResponse> response = CargoWebMapper.toFindAllResponse(cargos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponse> findCargoById(@PathVariable UUID id) {
        Cargo cargo = cargoService.getCargo(id);

        CargoResponse response = CargoWebMapper.toResponse(cargo);

        return ResponseEntity.ok(response);
    }
}
