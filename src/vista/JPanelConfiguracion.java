package vista;

import javax.swing.JPanel;
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

public class JPanelConfiguracion extends JPanel{
	private Font fuenteGrande = new Font("Comic Sans MS", Font.BOLD, 50);
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	// Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	//Textos y frases
	private JLabel datosEmpresa = new JLabel("Datos de la Empresa");
	private JLabel ruc = new JLabel("Ruc:");
	private JTextField rucTexto = new JTextField(20);
	private JLabel nombre = new JLabel("Nombre:");
	private JTextField nombreTexto = new JTextField(20);
	private JLabel telefono = new JLabel("Teléfono:");
	private JTextField telefonoTexto = new JTextField(20);
	private JLabel direccion = new JLabel("Dirección:");
	private JTextField direccionTexto = new JTextField(20);
	private JLabel razonSocial = new JLabel("Razón Social:");
	private JTextField razonSocialTexto = new JTextField(20);

	//Declaración iconos de los botones
	private ImageIcon iconoGuardar = new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png"));

	//Declaración botones
	private JButton guardarBoton = new JButton(iconoGuardar);

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	// Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	private DatosIncorrectos datosFrame = new DatosIncorrectos("Los datos introducidos son incorrectos o estan vacios");	
	private Guardado guardado = new Guardado("La configuración introducida se ha guardado");

	public JPanelConfiguracion(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		datosEmpresa.setFont(fuenteGrande); //Para poner la fuente que hemos creado previamente
		ruc.setFont(fuenteChica);
		nombre.setFont(fuenteChica);
		telefono.setFont(fuenteChica);
		direccion.setFont(fuenteChica);
		razonSocial.setFont(fuenteChica);
		
		
		guardarBoton.setToolTipText("Guardar"); //"setToolTipText", para poner un texto alternativo cuando el raton pasa por encima
		

		config.gridx=0;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(5, 360, 5, 0);
		this.add(datosEmpresa, config);

		config.gridx=0;
		config.gridy=1;
		config.gridwidth=1; //Para ocupar dos huecos en el eje x
		config.gridheight=1;
		config.insets = new Insets(40, 120, 5, 620); //Para poner margenes
		this.add(ruc, config);
		config.gridx=0;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, 120, 40, 620);
		this.add(rucTexto, config);

		config.gridx=1;
		config.gridy=1;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(40, -500, 5, 500);
		this.add(nombre, config);
		config.gridx=1;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, -500, 40, 500);
		this.add(nombreTexto, config);

		config.gridx=2;
		config.gridy=1;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(40, -380, 5, 160);
		this.add(telefono, config);
		config.gridx=2;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, -380, 40, 160);
		this.add(telefonoTexto, config);

		config.gridx=0;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, 120, 5, 620);
		this.add(direccion, config);
		config.gridx=0;
		config.gridy=4;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, 120, 140, 620);
		this.add(direccionTexto, config);

		config.gridx=1;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, -500, 5, 500);
		this.add(razonSocial, config);
		config.gridx=1;
		config.gridy=4;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(5, -500, 140, 500);
		this.add(razonSocialTexto, config);

		config.gridx=1;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		config.fill = GridBagConstraints.CENTER; //Para ocupar el centro
		config.insets = new Insets(5, -500, 35, 500);
		this.add(guardarBoton, config);


		guardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarConfiguracion();
			}
		});
	}


	private void guardarConfiguracion() {
		String rucT = rucTexto.getText();
		String nombreT = nombreTexto.getText();
		String telefonoT = telefonoTexto.getText();
		String direccionT = direccionTexto.getText();
		String razonSocialT = razonSocialTexto.getText();

		// ".isEmpty()", para saber si no esta vacio
		if (!rucT.isEmpty() && validacion.validarNombre(nombreT) == true && validacion.validarNumeroTelefono(telefonoT) == true && validacion.validarDireccion(direccionT) == true && !razonSocialT.isEmpty()) {
			System.out.println("Entra");
			String valores = "'" + rucT + "', '" + nombreT + "', '" + telefonoT + "', '" + direccionT + "', '" + razonSocialT + "'";

			Conexion conexion = new Conexion();
			Connection cn = null;

			try {
				cn = conexion.conectar();
				conexion.deleteData("configuracion", "id");
				conexion.insertData("configuracion (ruc, nombre, telefono, direccion, mensaje)", valores);
				
				//Ponemos todos los campos de textos vacíos
				rucTexto.setText("");
				nombreTexto.setText("");
				telefonoTexto.setText("");
				direccionTexto.setText("");
				razonSocialTexto.setText("");
				
				guardado.setVisible(true);
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
			if (razonSocialT.isEmpty()) {
				mensaje = mensaje + " Razón Social";
			}
			
			datosFrame = new DatosIncorrectos(mensaje);
			datosFrame.setVisible(true);
			System.out.println("No entra");
		}
	}
}