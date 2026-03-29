package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.persistence;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;
import pl.mwisniewski.logitrack.orderService.cargo.domain.model.CargoStatus;
import pl.mwisniewski.logitrack.orderService.order.infrastructure.persistence.DistanceEmbeddable;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cargo")
@Getter
@Setter
public class CargoEntity implements Persistable<UUID> {

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

    @NotNull
    @Column(name = "order_id")
    private UUID orderId;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @Embedded
    private DimensionsEmbeddable dimensions;

    @Embedded
    private DistanceEmbeddable distance;

    @Embedded
    private WeightEmbeddable weight;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
