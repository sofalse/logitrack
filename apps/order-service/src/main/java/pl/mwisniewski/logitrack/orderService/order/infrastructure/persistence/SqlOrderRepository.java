package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.mwisniewski.logitrack.orderService.common.PageResult;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Order;
import pl.mwisniewski.logitrack.orderService.order.domain.ports.OrderRepository;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.web.OrderMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SqlOrderRepository implements OrderRepository {
    private final JpaOrderRepository jpaRepository;
    private final OrderMapper mapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = jpaRepository.findById(order.getId())
                .map(existing -> {
                    mapper.updateEntity(order, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(order));
        OrderEntity result = jpaRepository.save(orderEntity);

        return mapper.toDomain(result);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll(Sort.by("updatedAt").descending()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public PageResult<Order> findAll(int page, int size) {
        Page<OrderEntity> result = jpaRepository.findAll(PageRequest.of(page, size).withSort(Sort.by("updatedAt").descending()));
        return new PageResult<>(
                result.getContent().stream().map(mapper::toDomain).toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
