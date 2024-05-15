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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanelClientes extends JPanel{
	Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Creación tabla
	String[] cabezera = {"ID", "DNI", "NOMBRE", "TELÉFONO", "DIRECCIÓN"};

	//Declaración tabla
	JPanelTabla panelTabla = new JPanelTabla();
	JScrollPane tabla = panelTabla.getTabla(cabezera, panelTabla.datos("cliente"));

	//Textos y frases
	private JLabel dni = new JLabel("DNI:");
	private JTextField dniTexto = new JTextField(20);
	private JLabel nombre = new JLabel("NOMBRE:");
	private JTextField nombreTexto = new JTextField(20);
	private JLabel telefono = new JLabel("TELÉFONO:");
	private JTextField telefonoTexto = new JTextField(20);
	private JLabel direccion = new JLabel("DIRECCIÓN:");
	private JTextField direccionTexto = new JTextField(20);

	//Declaración iconos de los botones
	ImageIcon iconoGuardar = new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png"));
	ImageIcon iconoActualizar = new ImageIcon(Sistema.class.getResource("/img/actualizar.png"));
	ImageIcon iconoBorrar = new ImageIcon(Sistema.class.getResource("/img/borrar.png"));
	ImageIcon iconoNuevo = new ImageIcon(Sistema.class.getResource("/img/nuevo.png"));

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
	private Guardado guardado = new Guardado("El cliente introducido se ha guardado");

	public JPanelClientes(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		dni.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente
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
		config.insets = new Insets(50, 180, 5, 5); //Para poner margenes
		this.add(dni, config);
		config.gridx=2;
		config.gridy=0;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 5, 5, 5);
		this.add(dniTexto, config);

		config.gridx=0;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 134, 5, 5);
		this.add(nombre, config);
		config.gridx=2;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 5, 5, 5);
		this.add(nombreTexto, config);

		config.gridx=0;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 111, 5, 5);
		this.add(telefono, config);
		config.gridx=2;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 5, 5, 5);
		this.add(telefonoTexto, config);

		config.gridx=0;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 104, 5, 5);
		this.add(direccion, config);
		config.gridx=2;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(50, 5, 5, 5);
		this.add(direccionTexto, config);

		config.gridx=0;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		config.fill = GridBagConstraints.CENTER; //Para ocupar el centro
		config.insets = new Insets(5, 5, 5, 5);
		this.add(guardarBoton, config);

		config.gridx=1;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(actualizarBoton, config);

		config.gridx=2;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(borrarBoton, config);

		config.gridx=4;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=6;
		config.ipadx = 700; //Tamaño x
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(5, 15, 5, 5);
		this.add(tabla, config);


		guardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCliente();
				String[][] nuevosDatos = panelTabla.datos("cliente");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		actualizarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] nuevosDatos = panelTabla.datos("cliente");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		borrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar frame = new Borrar("cliente");
				frame.setVisible(true);
			}
		});
	}


	private void guardarCliente() {
		String dniT = dniTexto.getText();
		String nombreT = nombreTexto.getText();
		String telefonoT = telefonoTexto.getText();
		String direccionT = direccionTexto.getText();

		if (validacion.validarDNI(dniT) == true && validacion.validarNombre(nombreT) == true && validacion.validarNumeroTelefono(telefonoT) == true && validacion.validarDireccion(direccionT) == true) {
			String valores = "'" + dniT + "', '" + nombreT + "', '" + telefonoT + "', '" + direccionT + "'";

			Conexion conexion = new Conexion();
			Connection cn = null;

			try {
				cn = conexion.conectar();

				String[][] datosRepetidosClientes = conexion.getData("SELECT dni, nombre, telefono, direccion FROM cliente");

				if (!validacion.validarPalabraEnMatrizString(datosRepetidosClientes, dniT) && !validacion.validarPalabraEnMatrizString(datosRepetidosClientes, nombreT) && !validacion.validarPalabraEnMatrizString(datosRepetidosClientes, telefonoT) && !validacion.validarPalabraEnMatrizString(datosRepetidosClientes, direccionT)) {
					System.out.println("Ha entrado");
					conexion.insertData("cliente (dni, nombre, telefono, direccion)", valores);
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
			if (!validacion.validarDNI(dniT) == true) {
				mensaje = mensaje + " DNI";
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