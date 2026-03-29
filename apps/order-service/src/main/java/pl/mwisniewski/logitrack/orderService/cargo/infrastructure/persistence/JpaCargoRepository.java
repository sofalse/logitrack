package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaCargoRepository extends JpaRepository<CargoEntity, UUID> {
    List<CargoEntity> findAllByOrderId(UUID orderId);
}
