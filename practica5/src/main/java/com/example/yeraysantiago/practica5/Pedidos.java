package com.example.yeraysantiago.practica5;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Pedidos extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxUsuario;
    private JComboBox<String> comboBoxProducto;
    private JTextField CantidadTxT;
    private Interfaz parent;



    public Pedidos(Interfaz parent) {
    	this.parent = parent;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel primerLabel = new JLabel("Añadir Pedido");
        primerLabel.setBackground(Color.CYAN);
        primerLabel.setOpaque(true);
        primerLabel.setBounds(10, 11, 414, 44);
        primerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        primerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        contentPane.add(primerLabel);
        
        JButton Agregar = new JButton("Añadir");
        Agregar.setBounds(10, 203, 414, 23);
        contentPane.add(Agregar);
        
        JButton Atras = new JButton("Atras");
        Atras.setBounds(10, 227, 414, 23);
        contentPane.add(Atras);
        
        JLabel Nombre = new JLabel("Usuario");
        Nombre.setBounds(187, 55, 36, 14);
        contentPane.add(Nombre);
        
        JLabel Nombre_1 = new JLabel("Cantidad");
        Nombre_1.setBounds(187, 135, 43, 14);
        contentPane.add(Nombre_1);
        
        CantidadTxT = new JTextField();
        CantidadTxT.setBounds(10, 157, 414, 44);
        contentPane.add(CantidadTxT);
        
        comboBoxUsuario = new JComboBox<>();
        comboBoxUsuario.setBounds(10, 72, 414, 23);
        contentPane.add(comboBoxUsuario);
        
        JLabel lblProducto = new JLabel("Producto");
        lblProducto.setBounds(187, 97, 61, 14);
        contentPane.add(lblProducto);
        
        comboBoxProducto = new JComboBox<>();
        comboBoxProducto.setBounds(10, 112, 414, 23);
        contentPane.add(comboBoxProducto);
        

        actualizarComboBox();


        Agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productoSeleccionado = (String) comboBoxProducto.getSelectedItem();
                String usuarioSeleccionado = (String) comboBoxUsuario.getSelectedItem();
                Integer cantidad;
                
                try {
                    cantidad = Integer.valueOf(CantidadTxT.getText());
                } catch (NumberFormatException ex) {
                    System.out.println("Cantidad no válida.");
                    return; 
                }
                
                System.out.println("Usuario seleccionado: " + usuarioSeleccionado);
                if (usuarioSeleccionado == null || usuarioSeleccionado.trim().isEmpty() || usuarioSeleccionado.equals("Nuevo Usuario")) {
                    System.out.println("Debes seleccionar un usuario existente.");
                    return;
                }
                
                Pattern pattern = Pattern.compile("id:\\s*(\\d+)");
                Matcher matcher = pattern.matcher(usuarioSeleccionado);
                String userId = "";
                if (matcher.find()) {
                    userId = matcher.group(1);
                    System.out.println("userId: " + userId);
                } else {
                    System.out.println("No ID encontrado para el usuario.");
                    return;
                }
                
                System.out.println("Producto seleccionado: " + productoSeleccionado); 

                Pattern pattern2 = Pattern.compile("id:\\s*(\\d+)");
                Matcher matcher2 = pattern2.matcher(productoSeleccionado);
                String productId = "";
                if (matcher2.find()) {
                    productId = matcher2.group(1);
                } else {
                    System.out.println("No ID encontrado para el producto.");
                    return; 
                }

                if(!userId.equals("") && !productId.equals("") && cantidad > 0) {
                    String URL_CONEXION = "jdbc:h2:file:~/test";
                    String username = "sa";
                    String password = "password";
                    
                    try {
                        Connection conn = DriverManager.getConnection(URL_CONEXION, username, password);
                        String sentenciaSQLAgregar = "INSERT INTO pedidos (id_usuario, id_producto, cantidad) VALUES (?, ?, ?)";
                        PreparedStatement pstmtAgregar = conn.prepareStatement(sentenciaSQLAgregar);
                        pstmtAgregar.setInt(1, Integer.valueOf(userId));
                        pstmtAgregar.setInt(2, Integer.valueOf(productId));
                        pstmtAgregar.setInt(3, cantidad);
                        
                        int filasAfectadas = pstmtAgregar.executeUpdate();
                        if (filasAfectadas > 0) {
                            System.out.println("Se insertó correctamente");
                            actualizarComboBox();
                        } else {
                            System.out.println("No se ha podido insertar");
                        }
                    
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Atras.addActionListener(e -> dispose());
    }
    public void actualizarComboBox() {
        comboBoxUsuario.removeAllItems();
        comboBoxProducto.removeAllItems();

        ArrayList<String> datosUsuarios = addChoicesToUserDropDown();
        for (String datoUsuario : datosUsuarios) {
            comboBoxUsuario.addItem(datoUsuario);
        }

        ArrayList<String> datosProducto = addChoicesToProductDropDown();
        for (String datoProducto : datosProducto) {
            comboBoxProducto.addItem(datoProducto);
        }
    }
    
    private ArrayList<String> addChoicesToUserDropDown(){
        String URL_CONEXION = "jdbc:h2:file:~/test";
        String usuario = "sa";
        String password = "password";
        Connection conn;
        ArrayList<String> choices = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
            Statement statement = conn.createStatement();
            String selectTableSQL ="SELECT * FROM usuarios";
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while(rs.next())
            {
                String id= rs.getString("id");
                String nombre = rs.getString("nombre");
                choices.add("id: " + id + " nombre: " + nombre);
            }
            rs.close();
            statement.close();
        }catch (SQLException ex) {
        	ex.printStackTrace();
            
        }
        return choices;
    }
    private ArrayList<String> addChoicesToProductDropDown() {
        String URL_CONEXION = "jdbc:h2:file:~/test";
        String usuario = "sa"; 
        String password = "password";
        Connection conn;
        ArrayList<String> choices = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
            Statement statement = conn.createStatement();
            String selectTableSQL = "SELECT * FROM productos"; 
            ResultSet rs = statement.executeQuery(selectTableSQL);
            
            while (rs.next()) {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                choices.add("id: " + id + " nombre: " + nombre); 
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return choices;
    }
}