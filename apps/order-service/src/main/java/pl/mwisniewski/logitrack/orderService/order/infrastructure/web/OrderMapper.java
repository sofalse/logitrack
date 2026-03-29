package pl.mwisniewski.logitrack.orderService.order.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.mwisniewski.logitrack.orderService.order.domain.model.*;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence.DistanceEmbeddable;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence.OrderEntity;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence.PriceEmbeddable;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence.RouteEmbeddable;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity toEntity(Order domain);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "new", ignore = true)
    void updateEntity(Order domain, @MappingTarget OrderEntity entity);

    Order toDomain(OrderEntity entity);


    @Mapping(target = "distance_value", source = "value")
    @Mapping(target = "distance_unit", source = "unit")
    DistanceEmbeddable map(Distance distance);

    @Mapping(target = "value", source = "distance_value")
    @Mapping(target = "unit", source = "distance_unit")
    Distance map(DistanceEmbeddable distanceEmbeddable);

    PriceEmbeddable map(Price price);
    Price map(PriceEmbeddable priceEmbeddable);

    RouteEmbeddable map(Route route);
    Route map(RouteEmbeddable routeEmbeddable);
}
