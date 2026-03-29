package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.domain.ports.CargoRepository;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.CargoMapper;
import pl.mwisniewski.logitrack.orderService.common.PageResult;

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
        return jpaRepository.findAll(Sort.by("updatedAt").descending()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public PageResult<Cargo> findAll(int page, int size) {
        Page<CargoEntity> result = jpaRepository.findAll(PageRequest.of(page, size).withSort(Sort.by("updatedAt").descending()));
        return new PageResult<>(
                result.getContent().stream().map(mapper::toDomain).toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
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
