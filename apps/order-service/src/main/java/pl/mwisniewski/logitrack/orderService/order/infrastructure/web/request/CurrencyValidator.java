package pl.mwisniewski.logitrack.orderService.order.infrastructure.web.request;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.mwisniewski.logitrack.orderService.order.domain.model.Price;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private static final Set<String> ALLOWED = Arrays.stream(Price.Currency.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED.contains(value);
    }
}
