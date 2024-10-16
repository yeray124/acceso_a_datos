package paquete_3_2;

public class CalculadoraDescuentos {

    public double aplicarDescuento(double precio, double porcentajeDescuento) {
        if (precio < 0 || porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("Precio y porcentaje de descuento deben ser positivos y el porcentaje debe estar entre 0 y 100.");
        }
        return precio - (precio * (porcentajeDescuento / 100));
    }
}