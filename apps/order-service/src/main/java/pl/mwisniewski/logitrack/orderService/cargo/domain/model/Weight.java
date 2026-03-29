package pl.mwisniewski.logitrack.orderService.cargo.domain.model;

import java.math.BigDecimal;

public record Weight(BigDecimal value, Unit unit) {
    public Weight {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
    }

    public Weight(int value, String unit) {
        this(new BigDecimal(value), Unit.valueOf(unit));
    }

    public enum Unit {KG, TON, LBS}
}
