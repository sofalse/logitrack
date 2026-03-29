package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.*;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence.CargoEntity;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence.DimensionsEmbeddable;
import pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence.WeightEmbeddable;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CargoEntity toEntity(Cargo domain);

    Cargo toDomain(CargoEntity entity);


    @Mapping(target = "weight_value", source = "value")
    @Mapping(target = "weight_unit", source = "unit")
    WeightEmbeddable map(Weight weight);

    @Mapping(target = "value", source = "weight_value")
    @Mapping(target = "unit", source = "weight_unit")
    Weight map(WeightEmbeddable weightEmbeddable);

    DimensionsEmbeddable map(Dimensions dimensions);
    Dimensions map(DimensionsEmbeddable dimensionsEmbeddable);
}
