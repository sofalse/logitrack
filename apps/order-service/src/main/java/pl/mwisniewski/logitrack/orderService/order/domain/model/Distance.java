package pl.mwisniewski.logitrack.orderService.order.domain.model;

import java.math.BigDecimal;

public record Distance(BigDecimal value, Unit unit) {
    public Distance {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
    }

    public Distance(double value, String unit) {
        this(BigDecimal.valueOf(value), Unit.valueOf(unit));
    }
    public enum Unit { KM, MI }

}
