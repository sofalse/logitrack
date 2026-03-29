package pl.mwisniewski.logitrack.orderService.cargo.domain.ports;

import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CargoRepository {
    Cargo save(Cargo cargo);

    List<Cargo> findAll();

    Optional<Cargo> findById(UUID id);

    List<Cargo> findAllByOrderId(UUID orderId);

    void deleteById(UUID id);
}
