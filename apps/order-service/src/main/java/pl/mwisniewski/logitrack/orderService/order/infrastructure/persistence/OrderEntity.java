package pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
import pl.mwisniewski.logitrack.orderService.order.domain.model.OrderStatus;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity implements Persistable<UUID> {

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    void markNotNew() {
        this.isNew = false;
    }

    @Id
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

    @NotNull
    private String issuer;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

}
