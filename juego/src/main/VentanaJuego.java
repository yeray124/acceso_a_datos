package main;

import javax.swing.JFrame;

public class VentanaJuego {
    private JFrame jframe;

    public VentanaJuego(PanelJuego juegoPanel) {
        jframe = new JFrame();
        jframe.setSize(400, 400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(juegoPanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}