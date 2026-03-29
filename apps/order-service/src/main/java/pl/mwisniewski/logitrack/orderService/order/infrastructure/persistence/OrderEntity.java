package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.mwisniewski.logitrack.orderService.order.domain.model.OrderStatus;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "expected_delivery_date")
    @NotNull
    private OffsetDateTime expectedDeliveryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    @Column(name = "expires_at")
    private Instant expiresAt;

    @NotNull
    @Embedded
    private RouteEmbeddable route;

    @NotNull
    @Embedded
    private PriceEmbeddable price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
