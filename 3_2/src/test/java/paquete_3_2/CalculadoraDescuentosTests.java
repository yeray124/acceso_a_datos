package paquete_3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraDescuentosTests {
	private CalculadoraDescuentos calculadoraDescuentos;
	private Tienda tienda;
	
	@BeforeEach
	public void SetUp() {
		calculadoraDescuentos = new CalculadoraDescuentos();
		tienda = new Tienda(calculadoraDescuentos);
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
								// TIENDA // 
	@Test
	public void testComprarProducto_ConOfertaYDescuento_DevuelvePrecioConDescuento() { //1
		 	assertEquals(80, tienda.ComprarProducto(100, 20, true)); 
	}
	@Test
	public void testComprarProducto_SinOferta_DevuelvePrecioCompleto() {  //2
	    assertEquals(100, tienda.ComprarProducto(100, 20, false));
	}

	@Test
	public void testComprarProducto_Descuento0PorCiento_DevuelvePrecioCompleto() {  //3
	    assertEquals(100, tienda.ComprarProducto(100, 0, true));
	}
	@Test
	public void testComprarProducto_Descuento100PorCiento_Devuelve0() {  //4
	    assertEquals(0, tienda.ComprarProducto(100, 100, true));
	}

	@Test
	public void testComprarProducto_Precio0_Devuelve0() {  //5
	    assertEquals(0, tienda.ComprarProducto(0, 50, true));
	}

	@Test
	public void testComprarProducto_PrecioNegativo_seLanzaExcepcion() { //6
	    assertThrows(IllegalArgumentException.class, () -> tienda.ComprarProducto(-50, 20, true));
	}
	@Test
	public void testComprarProducto_PorcentajeNegativo() { ///7
		assertThrows(IllegalArgumentException.class, () -> tienda.ComprarProducto(100, -20, true));
	}
	@Test
	public void testComprarProducto_PorcentajeMayorde100_seLanzaExcepcion() {  //8
	    assertThrows(IllegalArgumentException.class, () -> tienda.ComprarProducto(100, 120, true));
	}
	@Test
	public void testComprarProducto_Porcentaje100_OfertaEnFalse() { /// 9
		assertEquals(40, tienda.ComprarProducto(40, 100, false));
	}
	@Test
	public void testComprarProducto_DescuentoNegativo_OfertaEnFalse() {
		assertThrows(IllegalArgumentException.class, () -> tienda.ComprarProducto(100, -20, false));
	}

//	@Test
//	public void testComprarProducto_DescuentoMayorA100_OfertaEnFalse() {
//		assertThrows(IllegalArgumentException.class, () -> tienda.ComprarProducto(100, 150, false));
//	}
}