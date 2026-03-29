package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web;

import pl.mwisniewski.logitrack.orderService.cargo.domain.model.Cargo;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.response.CargoResponse;

import java.time.Instant;
import java.util.List;

public class CargoWebMapper {
    public static CargoResponse toResponse(Cargo cargo) {
        return new CargoResponse(
                cargo.getId(),
                cargo.getOrderId(),
                cargo.getDescription(),
                cargo.getDimensions().width(),
                cargo.getDimensions().length(),
                cargo.getDimensions().height(),
                cargo.getWeight().value(),
                cargo.getWeight().unit().toString(),
                cargo.getStatus().name(),
                Instant.now(),
                Instant.now()
        );
    }

    public static List<CargoResponse> toFindAllResponse(List<Cargo> cargos) {
        return cargos.stream().map(cargo -> new CargoResponse(
                cargo.getId(),
                cargo.getOrderId(),
                cargo.getDescription(),
                cargo.getDimensions().width(),
                cargo.getDimensions().length(),
                cargo.getDimensions().height(),
                cargo.getWeight().value(),
                cargo.getWeight().unit().toString(),
                cargo.getStatus().name(),
                Instant.now(),
                Instant.now()
        )).toList();
    }
}