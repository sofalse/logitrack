package pl.mwisniewski.logitrack.orderService.cargo.domain.exception;

public class CargoNotFoundException extends RuntimeException{
    public CargoNotFoundException(String id) {
        super("Cargo with id " + id + " not found");
    }
}
