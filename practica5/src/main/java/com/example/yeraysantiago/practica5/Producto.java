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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class Producto extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea TextoDos;
	private JTextArea TextoUno;

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
	public Producto(Interfaz parent) {
		super(parent, "Añadir Producto", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel primerLabel = new JLabel("Añadir Producto");
		primerLabel.setBackground(Color.CYAN);
		primerLabel.setOpaque(true);
		primerLabel.setBounds(10, 11, 414, 44);
		primerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		primerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		contentPane.add(primerLabel);
		
		JButton Boton2 = new JButton("Añadir");
		Boton2.setBounds(10, 192, 414, 23);
		contentPane.add(Boton2);
		
		JButton Boton3 = new JButton("Atras");
		Boton3.setBounds(10, 227, 414, 23);
		contentPane.add(Boton3);
		
		JLabel Nombre = new JLabel("Nombre de producto");
		Nombre.setBounds(162, 58, 131, 14);
		primerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Nombre);
		
		JLabel Nombre_1 = new JLabel("Precio de Producto");
		Nombre_1.setBounds(162, 121, 131, 14);
		contentPane.add(Nombre_1);
		
        TextoUno = new JTextArea();
        TextoUno.setBounds(10, 77, 414, 44);
        contentPane.add(TextoUno);
        
        TextoDos = new JTextArea();
        TextoDos.setBounds(10, 137, 414, 44);
        contentPane.add(TextoDos);
		
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
		String username = TextoUno.getText();
		Double precio = Double.valueOf(TextoDos.getText());
		
		if(!username.equals("")){
			String URL_CONEXION = "jdbc:h2:file:~/test", usuario = "sa", password = "password";
			try {
				Connection conectar = DriverManager.getConnection(URL_CONEXION, usuario, password);
				Statement statementAgregar = conectar.createStatement();
				String sentenciaSQLAgregar = "INSERT INTO productos (Nombre, Precio) VALUES (?, ?)";
				PreparedStatement Agregar = conectar.prepareStatement(sentenciaSQLAgregar);
				
				Agregar.setString(1,username);
				Agregar.setDouble(2, precio);
				int filasAfectadas = Agregar.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("Se insertó correctamente");
				} else {
					System.out.println("No se pudo insertar");
				}
				
			} catch (SQLException ex) {}
		} else {
			System.out.println("El campo de nombre de usuario esta vacio.");
		}
	}
}