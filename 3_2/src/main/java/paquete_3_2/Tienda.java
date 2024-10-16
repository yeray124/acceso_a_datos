package paquete_3_2;

public class Tienda {
    private CalculadoraDescuentos calculadora;

    public Tienda(CalculadoraDescuentos calculadora) {
        this.calculadora = calculadora;
    }

    public double ComprarProducto(double precio, double porcentajeDescuento, Boolean oferta) {
        if (oferta) {
            return calculadora.aplicarDescuento(precio, porcentajeDescuento);
        } else {
            return precio;
        }
    }
}