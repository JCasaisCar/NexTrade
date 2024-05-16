package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bbdd.Conexion;
import validaciones.Validaciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelProveedor extends JPanel{
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	//Creación tabla
	private String[] cabezera = {"ID", "RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"};

	//Declaración tabla
	private JPanelTabla panelTabla = new JPanelTabla();
	private JScrollPane tabla = panelTabla.getTabla(cabezera, panelTabla.datos("proveedor"));

	//Textos y frases
	private JLabel ruc = new JLabel("RUC:");
	private JTextField rucTexto = new JTextField(20);
	private JLabel nombre = new JLabel("NOMBRE:");
	private JTextField nombreTexto = new JTextField(20);
	private JLabel telefono = new JLabel("TELÉFONO:");
	private JTextField telefonoTexto = new JTextField(20);
	private JLabel direccion = new JLabel("DIRECCIÓN:");
	private JTextField direccionTexto = new JTextField(20);

	//Declaración iconos de los botones
	private ImageIcon iconoGuardar = new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png"));
	private ImageIcon iconoActualizar = new ImageIcon(Sistema.class.getResource("/img/actualizar.png"));
	private ImageIcon iconoBorrar = new ImageIcon(Sistema.class.getResource("/img/borrar.png"));

	//Declaración botones
	private JButton guardarBoton = new JButton(iconoGuardar);
	private JButton actualizarBoton = new JButton(iconoActualizar);
	private JButton borrarBoton = new JButton(iconoBorrar);

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	private DatosIncorrectos datosFrame;
	private DatosIncorrectos datosFrame1 = new DatosIncorrectos("Los datos introducidos estan repetidos");
	private Guardado guardado = new Guardado("El proveedor introducido se ha guardado");

	public JPanelProveedor(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		ruc.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente
		nombre.setFont(fuenteChica);
		telefono.setFont(fuenteChica);
		direccion.setFont(fuenteChica);
		
		
		guardarBoton.setToolTipText("Guardar"); //"setToolTipText", para poner un texto alternativo cuando el raton pasa por encima
		actualizarBoton.setToolTipText("Actualizar");
		borrarBoton.setToolTipText("Borrar");


		config.gridx=0;
		config.gridy=0;
		config.gridwidth=2; //Para ocupar dos huecos en el eje x
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 0.0;
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(37, 185, 5, 5); //Para poner margenes
		this.add(ruc, config);
		config.gridx=2;
		config.gridy=0;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -40, 5, 5);
		this.add(rucTexto, config);

		config.gridx=0;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 134, 5, 5);
		this.add(nombre, config);
		config.gridx=2;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -40, 5, 5);
		this.add(nombreTexto, config);

		config.gridx=0;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 111, 5, 5);
		this.add(telefono, config);
		config.gridx=2;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -40, 5, 5);
		this.add(telefonoTexto, config);

		config.gridx=0;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 103, 5, 5);
		this.add(direccion, config);
		config.gridx=2;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -40, 5, 5);
		this.add(direccionTexto, config);

		config.gridx=0;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(30, 5, 5, 5);
		config.fill = GridBagConstraints.CENTER; //Para ocupar el centro
		this.add(guardarBoton, config);

		config.gridx=2;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(actualizarBoton, config);

		config.gridx=0;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, 5, 5, 5);
		this.add(borrarBoton, config);

		config.gridx=4;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=7;
		config.ipadx = 700; //Tamaño x
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(5, 15, 5, 5);
		this.add(tabla, config);


		guardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarProveedor();
				String[][] nuevosDatos = panelTabla.datos("proveedor");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		actualizarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] nuevosDatos = panelTabla.datos("proveedor");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		borrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar frame = new Borrar("proveedor");
				frame.setVisible(true); 
			}
		}
				);
	}


	private void guardarProveedor() {
		//Conexión a la base de datos y consulta SQL para agregar un usuario
		String rucT = rucTexto.getText();
		String nombreT = nombreTexto.getText();
		String telefonoT = telefonoTexto.getText();
		String direccionT = direccionTexto.getText();

		//".isEmpty()", para saber si no esta vacío
		if (!rucT.isEmpty() && validacion.validarNombre(nombreT) == true && validacion.validarNumeroTelefono(telefonoT) == true && validacion.validarDireccion(direccionT) == true) {
			String valores = "'" + rucT + "', '" + nombreT + "', '" + telefonoT + "', '" + direccionT + "'";

			Conexion conexion = new Conexion();
			Connection cn = null;

			try {
				cn = conexion.conectar();

				String[][] datosRepetidosProveedores = conexion.getData("SELECT ruc, nombre, telefono, direccion FROM proveedor");

				if (!validacion.validarPalabraEnMatrizString(datosRepetidosProveedores, rucT) && !validacion.validarPalabraEnMatrizString(datosRepetidosProveedores, nombreT) && !validacion.validarPalabraEnMatrizString(datosRepetidosProveedores, telefonoT) && !validacion.validarPalabraEnMatrizString(datosRepetidosProveedores, direccionT)) {
					System.out.println("Ha entrado");
					conexion.insertData("proveedor (ruc, nombre, telefono, direccion)", valores);
					
					//Ponemos todos los campos de textos vacíos
					rucTexto.setText("");
					nombreTexto.setText("");
					telefonoTexto.setText("");
					direccionTexto.setText("");
					
					guardado.setVisible(true);
				} else {
					System.out.println("No ha entrado");
					datosFrame1.setVisible(true);
				}
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		} else {
			String mensaje = "Estos datos son incorrectos:";
			
			if (rucT.isEmpty()) {
				mensaje = mensaje + " RUC";
			}
			if (!validacion.validarNombre(nombreT) == true) {
				mensaje = mensaje + " Nombre";
			}
			if (!validacion.validarNumeroTelefono(telefonoT) == true) {
				mensaje = mensaje + " Teléfono";
			}
			if (!validacion.validarDireccion(direccionT) == true) {
				mensaje = mensaje + " Dirección";
			}
			datosFrame = new DatosIncorrectos(mensaje);
			datosFrame.setVisible(true);
		}
	}
}