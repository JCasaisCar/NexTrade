package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bbdd.Conexion;
import html.AbrirHTML;
import html.ExportarHTML;
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

public class JPanelProductos extends JPanel{
	Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Creacion tabla
	String[] cabezera = {"ID", "CÓDIGO", "NOMBRE", "PROVEEDOR", "STOCK", "PRECIO"};

	//Declaración tabla
	JPanelTabla panelTabla = new JPanelTabla();
	JScrollPane tabla = panelTabla.getTabla(cabezera, panelTabla.datos("producto"));

	//Textos y frases
	private JLabel codigo = new JLabel("CÓDIGO:");
	private JTextField codigoTexto = new JTextField(20);
	private JLabel nombre = new JLabel("NOMBRE:");
	private JTextField nombreTexto = new JTextField(20);
	private JLabel proveedor = new JLabel("PROVEEDOR:");
	private JTextField proveedorTexto = new JTextField(20);
	private JLabel precio = new JLabel("PRECIO:");
	private JTextField precioTexto = new JTextField(20);
	private JLabel cantidad = new JLabel("CANTIDAD:");
	private JTextField cantidadTexto = new JTextField(20);

	//Declaración iconos de los botones
	ImageIcon iconoGuardar = new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png"));
	ImageIcon iconoActualizar = new ImageIcon(Sistema.class.getResource("/img/actualizar.png"));
	ImageIcon iconoBorrar = new ImageIcon(Sistema.class.getResource("/img/borrar.png"));
	ImageIcon iconoPrint = new ImageIcon(Sistema.class.getResource("/img/print.png"));

	//Declaración botones
	private JButton guardarBoton = new JButton(iconoGuardar);
	private JButton actualizarBoton = new JButton(iconoActualizar);
	private JButton borrarBoton = new JButton(iconoBorrar);
	private JButton printBoton = new JButton(iconoPrint);

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	private DatosIncorrectos datosFrame;
	private DatosIncorrectos datosFrame1 = new DatosIncorrectos("El proveedor escrito es incorrecto o esta vacio");
	private DatosIncorrectos datosFrame2 = new DatosIncorrectos("Los datos introducidos estan repetidos");
	private Guardado guardado = new Guardado("El producto introducido se ha guardado");
	private AbrirHTML abrirHTML = new AbrirHTML();

	public JPanelProductos(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		codigo.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente
		nombre.setFont(fuenteChica);
		proveedor.setFont(fuenteChica);
		precio.setFont(fuenteChica);
		cantidad.setFont(fuenteChica);
		
		
		guardarBoton.setToolTipText("Guardar"); //"setToolTipText", para poner un texto alternativo cuando el raton pasa por encima
		actualizarBoton.setToolTipText("Actualizar");
		borrarBoton.setToolTipText("Borrar");
		printBoton.setToolTipText("Imprimir");


		config.gridx=0;
		config.gridy=0;
		config.gridwidth=2; //Para ocupar dos huecos en el eje x
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 0.0;
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(37, 137, 5, 5); //Para poner margenes
		this.add(codigo, config);
		config.gridx=2;
		config.gridy=0;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -80, 5, 5);
		this.add(codigoTexto, config);

		config.gridx=0;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 132, 5, 5);
		this.add(nombre, config);
		config.gridx=2;
		config.gridy=1;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -80, 5, 5);
		this.add(nombreTexto, config);

		config.gridx=0;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 98, 5, 5);
		this.add(proveedor, config);
		config.gridx=2;
		config.gridy=2;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -80, 5, 5);
		this.add(proveedorTexto, config);

		config.gridx=0;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 142, 5, 5);
		this.add(precio, config);
		config.gridx=2;
		config.gridy=3;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -80, 5, 5);
		this.add(precioTexto, config);

		config.gridx=0;
		config.gridy=4;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, 105, 5, 5);
		this.add(cantidad, config);
		config.gridx=2;
		config.gridy=4;
		config.gridwidth=2;
		config.gridheight=1;
		config.insets = new Insets(37, -80, 5, 5);
		this.add(cantidadTexto, config);

		config.gridx=0;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		config.fill = GridBagConstraints.CENTER; //Para ocupar el centro
		config.insets = new Insets(37, 5, 5, 5);
		this.add(guardarBoton, config);

		config.gridx=1;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(actualizarBoton, config);

		config.gridx=0;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(borrarBoton, config);

		config.gridx=1;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(printBoton, config);

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


		actualizarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] nuevosDatos = panelTabla.datos("producto");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		guardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarProducto();
				String[][] nuevosDatos = panelTabla.datos("producto");
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
			}
		});


		borrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar frame = new Borrar("producto");
				frame.setVisible(true); 
			}
		});


		printBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarHTML html = new ExportarHTML();
				String nombreArchivo = "productos";

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();

					html.exportarHTML(panelTabla.datos("producto"), cabezera, nombreArchivo, conexion.getONEData("SELECT nombre FROM configuracion"), conexion.getONEData("SELECT telefono FROM configuracion"), conexion.getONEData("SELECT direccion FROM configuracion"));

					abrirHTML.abrirHTMLenNavegador(nombreArchivo);
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		}
				);
	}


	private void guardarProducto() {
		//Conexión a la base de datos y consulta SQL para agregar un usuario
		String codigoT = codigoTexto.getText();
		String nombreT = nombreTexto.getText();
		String proveedorT = proveedorTexto.getText();
		String precioT = precioTexto.getText();
		String cantidadT = cantidadTexto.getText();
		
		if (!codigoT.isEmpty() && validacion.validarNombre(nombreT) == true && validacion.esNumeroFloat(precioT) == true && validacion.esEntero(proveedorT) == true && validacion.esEntero(cantidadT) == true) {
			String valores = "'" + codigoT + "', '" + nombreT + "', " + proveedorT + ", " + precioT + ", " + cantidadT + "";

			Conexion conexion = new Conexion();
			Connection cn = null;

			try {
				cn = conexion.conectar();

				String[][] proveedores = conexion.getData("SELECT id FROM proveedor");
				
				//".isEmpty()", para saber si no esta vacío
				if (!proveedorT.isEmpty() && validacion.validarPalabraEnMatrizString(proveedores, proveedorT)) {
					System.out.println("Ha entrado");

					String[][] datosRepetidosProductos = conexion.getData("SELECT codigo, nombre FROM producto");

					if (!validacion.validarPalabraEnMatrizString(datosRepetidosProductos, codigoT) && !validacion.validarPalabraEnMatrizString(datosRepetidosProductos, nombreT)) {
						System.out.println("Ha entrado");
						conexion.insertData("producto (codigo, nombre, proveedor, precio, stock)", valores);
						guardado.setVisible(true);
					} else {
						System.out.println("No ha entrado");
						datosFrame2.setVisible(true);
					}
				} else {
					System.out.println("No ha entrado");
					datosFrame1.setVisible(true);
				}
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		} else {
			String mensaje = "Estos datos son incorrectos:";
			
			if (codigoT.isEmpty()) {
				mensaje = mensaje + " Código";
			}
			if (!validacion.validarNombre(nombreT) == true) {
				mensaje = mensaje + " Nombre";
			}
			if (!validacion.esEntero(proveedorT) == true) {
				mensaje = mensaje + " Proveedor";
			}
			if (!validacion.esNumeroFloat(precioT) == true) {
				mensaje = mensaje + " Precio";
			}
			if (!validacion.esEntero(cantidadT) == true) {
				mensaje = mensaje + " Cantidad";
			}
			datosFrame = new DatosIncorrectos(mensaje);
			datosFrame.setVisible(true);
		}
	}
}