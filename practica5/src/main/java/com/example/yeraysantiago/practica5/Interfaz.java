package com.example.yeraysantiago.practica5;

import java.awt.EventQueue;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JDialog Usuario;
    private JDialog Producto;
    private JDialog Pedidos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel primerLabel = new JLabel("Gestor de Usuarios");
		primerLabel.setBackground(Color.CYAN);
		primerLabel.setOpaque(true);
		primerLabel.setBounds(10, 11, 414, 70);
		primerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		primerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		contentPane.add(primerLabel);
		
		JButton Boton1 = new JButton("Añadir Usuarios");
		Boton1.setBounds(10, 92, 414, 44);
		contentPane.add(Boton1);
		
		JButton Boton2 = new JButton("Añadir Producto");
		Boton2.setBounds(10, 151, 414, 44);
		contentPane.add(Boton2);
		
		JButton Boton3 = new JButton("Añadir Pedido");
		Boton3.setBounds(10, 206, 414, 44);
		contentPane.add(Boton3);
		
		
        Boton1.addActionListener(e -> {
            Usuario.setVisible(true);
        });

        Boton2.addActionListener(e -> {
            Producto.setVisible(true);
        });

        Boton3.addActionListener(e -> {
            Pedidos.setVisible(true);
        });

        DialogosLanzar();
    }


    private void DialogosLanzar() {
        Usuario = new Usuario(this);
        Usuario.setSize(500, 300);
        Usuario.setLocationRelativeTo(this);

        Producto = new Producto(this); 
        Producto.setSize(500, 300);
        Producto.setLocationRelativeTo(this);

        Pedidos = new Pedidos(this); 
        Pedidos.setSize(500, 300);
        Pedidos.setLocationRelativeTo(this);
    }
}