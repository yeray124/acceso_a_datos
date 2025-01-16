package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelJuego extends JPanel {
//Esta clase se encarga de dibujar
	public PanelJuego() {
		
	}
	
	//Para dibujar necesitas un jpanel pero no es el que dibuja es el contenedor
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
	}

}
