package pl.mwisniewski.logitrack.orderService.cargo.domain.ports;

import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.common.PageResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CargoRepository {
    Cargo save(Cargo cargo);

    List<Cargo> findAll();

    PageResult<Cargo> findAll(int page, int size);

    Optional<Cargo> findById(UUID id);

    List<Cargo> findAllByOrderId(UUID orderId);

    void deleteById(UUID id);
}
