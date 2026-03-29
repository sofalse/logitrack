package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.domain.ports.CargoRepository;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.CargoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class SqlCargoRepository implements CargoRepository {
    private final JpaCargoRepository jpaRepository;
    private final CargoMapper mapper;

    @Override
    public Optional<Cargo> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cargo> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Cargo> findAllByOrderId(UUID orderId) {
        return jpaRepository.findAllByOrderId(orderId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Cargo save(Cargo cargo) {
        CargoEntity cargoEntity = mapper.toEntity(cargo);
        CargoEntity result = jpaRepository.save(cargoEntity);

        return mapper.toDomain(result);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
