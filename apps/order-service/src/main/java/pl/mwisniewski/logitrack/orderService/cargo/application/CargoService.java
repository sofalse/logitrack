package pl.mwisniewski.logitrack.orderService.cargo.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mwisniewski.logitrack.orderService.cargo.application.dto.CreateCargoCommand;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.CargoStatus;
import pl.mwisniewski.logitrack.orderService.cargo.domain.ports.CargoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;

    @Transactional
    public Cargo createCargo(CreateCargoCommand command) {
        UUID id = UUID.randomUUID();

        Cargo newCargo = new Cargo(
                id,
                null,
                command.description(),
                CargoStatus.PENDING,
                command.dimensions(),
                command.weight()
        );

        // @TODO: Business logic here

        cargoRepository.save(newCargo);

        return newCargo;
    }

    @Transactional
    public List<Cargo> findAllCargos() {
        return cargoRepository.findAll();
    }

    @Transactional
    public Cargo getCargo(UUID id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Cargo> findCargosByOrderId(UUID orderId) {
        return cargoRepository.findAllByOrderId(orderId);
    }
}
