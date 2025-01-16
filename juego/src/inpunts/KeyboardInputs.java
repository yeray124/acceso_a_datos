package inpunts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

	// Movimientos ponemos a la escucha las teclas que van a hacer una aciion o click.
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Has presionado algo");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Has presionado algo");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Has presionado algo");
		
	}

}
