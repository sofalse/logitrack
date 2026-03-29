package pl.mwisniewski.logitrack.orderService.order.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super("Order with id " + id + " not found");
    }
}
