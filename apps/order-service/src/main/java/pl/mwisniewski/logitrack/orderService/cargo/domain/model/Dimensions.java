package pl.mwisniewski.logitrack.orderService.cargo.domain.model;

public record Dimensions(double length, double width, double height)  {
    public double volume() {
        return width * height * length;
    }
}