package pl.mwisniewski.logitrack.orderService.order.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
