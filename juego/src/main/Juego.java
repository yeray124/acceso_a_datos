package main;

public class Juego {
	private VentanaJuego ventanaJuego;
	private PanelJuego panelJuego;
	
	public Juego() {
		panelJuego = new PanelJuego();
		ventanaJuego = new VentanaJuego(panelJuego);
		panelJuego.setFocusable(true);
		panelJuego.requestFocus(); // Establece un foco de entrada para los eventos
		
	}
}