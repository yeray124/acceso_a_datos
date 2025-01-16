package main;

import java.awt.Graphics;


import javax.swing.JPanel;

import inpunts.KeyboardInputs;

public class PanelJuego extends JPanel {
//Esta clase se encarga de dibujar
	public PanelJuego() {
		
		//Llamamos a la clase que gestiona las llamadas de eventos
		addKeyListener(new KeyboardInputs());
		setFocusable(true);
        requestFocusInWindow();
	}
	
	//Para dibujar necesitas un jpanel pero no es el que dibuja es el contenedor
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(100, 100, 200, 50);
		
	} 
}