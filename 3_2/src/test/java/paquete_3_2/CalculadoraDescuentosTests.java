package paquete_3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraDescuentosTests {
	private CalculadoraDescuentos calculadoraDescuentos;
	
	@BeforeEach
	public void SetUp() {
		calculadoraDescuentos = new CalculadoraDescuentos();
	}
	
	@Test
	public void testAplicarDescuento_precioNegativo_seLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> calculadoraDescuentos.aplicarDescuento(-100, 20));
	}
	
	@Test
	public void testAplicarDescuento_descuentoNegativo_seLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> calculadoraDescuentos.aplicarDescuento(50, -20));
	}
	
	@Test
	public void testAplicarDescuento_descuentoMayorDeCien_seLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> calculadoraDescuentos.aplicarDescuento(50, 150));
	}
	@Test
	public void testAplicarDescuento_20Porcientode100_Devuelve80() {
			assertEquals(80, calculadoraDescuentos.aplicarDescuento(100,20));
	}
	@Test
	public void testAplicarDescuentoTotal_Devuelve0() {
			assertEquals(0, calculadoraDescuentos.aplicarDescuento(100,100)); // El primer 0 es el resultado que esperamos que devuelva.
	}
	@Test
	public void testAplicarDescuentoEs0_DevuelvePrecioCompleto() {
			assertEquals(100, calculadoraDescuentos.aplicarDescuento(100,0)); 
	}
	@Test
	public void testAplicarPrecioEs0_DevuelvePrecio0() {
			assertEquals(0, calculadoraDescuentos.aplicarDescuento(0,100)); 
	}
}
