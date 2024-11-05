package com.example.yeraysantiago.practica5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class Usuario extends JDialog  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea Textoo;

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
	
	public Usuario(JFrame parent) {
		super(parent, "Añadir Usuario", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel primerLabel = new JLabel("Añadir Usuario");
		primerLabel.setBackground(Color.CYAN);
		primerLabel.setOpaque(true);
		primerLabel.setBounds(10, 11, 414, 44);
		primerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		primerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
		contentPane.add(primerLabel);
		
		JButton Boton2 = new JButton("Añadir");
		Boton2.setBounds(10, 151, 414, 44);
		contentPane.add(Boton2);
		
		JButton Boton3 = new JButton("Atras");
		Boton3.setBounds(10, 206, 414, 44);
		contentPane.add(Boton3);
		
		JLabel Nombre = new JLabel("Nombre de usuario");
		Nombre.setBounds(167, 66, 131, 14);
		primerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Nombre);
		
        Textoo = new JTextArea(); 
        Textoo.setBounds(10, 80, 414, 64);
        contentPane.add(Textoo);
		
        Boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                addUserButtonActionPerformed(evento);
            }
        });

        Boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            	dispose(); 
            }
        });
		
	}

	
	public void addUserButtonActionPerformed(java.awt.event.ActionEvent evento) {
		String username = Textoo.getText().trim(); 
		if(!username.equals("")){
			String URL_CONEXION = "jdbc:h2:file:~/test", usuario = "sa", password = "password";
			try {
				Connection conectar = DriverManager.getConnection(URL_CONEXION, usuario, password);
				Statement statementAgregar = conectar.createStatement();
				String sentenciaSQLAgregar = "INSERT INTO usuarios (Nombre) VALUES (?)";
				PreparedStatement Agregar = conectar.prepareStatement(sentenciaSQLAgregar);
				Agregar.setString(1,username);
				int filasAfectadas = Agregar.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("Se inserto correctamente");
				} else {
					System.out.println("No se pudo insertar");
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("El campo de nombre de usuario esta vacio.");
		}
	}
}