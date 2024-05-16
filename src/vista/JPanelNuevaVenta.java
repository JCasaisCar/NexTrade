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

public class JPanelNuevaVenta extends JPanel{
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	private Conexion conex = new Conexion();
	private int idVenta = conex.obtenerUltimoIdVenta();
	private String condicion = " WHERE id_venta = " + idVenta;

	//Creación tabla
	private String[] cabezera = {"ID", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"};

	//Declaración tabla
	private JPanelTabla panelTabla = new JPanelTabla();
	private JScrollPane tabla = panelTabla.getTabla(cabezera, panelTabla.datosFiltradosColumnas("detalle", "id, nombreProducto, cantidad, precioProducto, total", condicion));

	//Textos y frases
	private JLabel codigo = new JLabel("Código:");
	private JTextField codigoTexto = new JTextField(20);
	private JLabel producto = new JLabel("Producto:");
	private JTextField productoTexto = new JTextField(20);
	private JLabel cantidad = new JLabel("Cantidad:");
	private JTextField cantidadTexto = new JTextField(20);
	private JLabel precio = new JLabel("Precio:");
	private JTextField precioTexto = new JTextField(20);
	private JLabel stock = new JLabel("Stock Dis:");
	private JTextField stockTexto = new JTextField(20);
	private JLabel cliente = new JLabel("Cliente:");
	private JTextField clienteTexto = new JTextField(20);
	private JLabel nombre = new JLabel("Nombre:");
	private JTextField nombreTexto = new JTextField(20);
	private JLabel totalAPagar = new JLabel("Total a pagar:");
	private JLabel dineroParaPagar = new JLabel("----€");
	private JLabel fecha = new JLabel("Escribe la fecha:");
	private JTextField fechaTexto = new JTextField(20);

	//Declaración iconos de los botones
	private ImageIcon iconoPrint = new ImageIcon(Sistema.class.getResource("/img/print.png"));
	private ImageIcon iconoVenta = new ImageIcon(Sistema.class.getResource("/img/torta.png"));
	private ImageIcon iconoBorrar = new ImageIcon(Sistema.class.getResource("/img/borrar.png"));
	private ImageIcon iconoDetalle = new ImageIcon(Sistema.class.getResource("/img/report.png"));

	//Declaración botones
	private JButton printBoton = new JButton(iconoPrint);
	private JButton detalleBoton = new JButton(iconoDetalle);
	private JButton borrarBoton = new JButton(iconoBorrar);
	private JButton ventaBoton = new JButton(iconoVenta);

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	private DatosIncorrectos datosFrame;
	private DatosIncorrectos datosFrame1 = new DatosIncorrectos("El código del producto introducido es incorrecto o esta vacio");
	private DatosIncorrectos datosFrame2 = new DatosIncorrectos("El ID del cliente introducido es incorrecto o esta vacio");
	private DatosIncorrectos datosFrame3 = new DatosIncorrectos("El nombre del cliente introducido es incorrecto o esta vacio");
	private DatosIncorrectos datosFrame4 = new DatosIncorrectos("El nombre del producto introducido es incorrecto o esta vacio");
	private DatosIncorrectos datosFrame5 = new DatosIncorrectos("La fecha introducida es incorrecta o esta vacia");
	private Guardado guardado = new Guardado("La venta se ha guardado");
	private CantidadIncorrecta cantidadFrame = new CantidadIncorrecta();
	private AbrirHTML abrirHTML = new AbrirHTML();

	public JPanelNuevaVenta(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		codigo.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente
		producto.setFont(fuenteChica);
		cantidad.setFont(fuenteChica);
		precio.setFont(fuenteChica);
		stock.setFont(fuenteChica);
		cliente.setFont(fuenteChica);
		nombre.setFont(fuenteChica);
		totalAPagar.setFont(fuenteChica);
		dineroParaPagar.setFont(fuenteChica);
		fecha.setFont(fuenteChica);
		
		printBoton.setToolTipText("Imprimir"); //"setToolTipText", para poner un texto alternativo cuando el raton pasa por encima
		detalleBoton.setToolTipText("Guardar Registro");
		borrarBoton.setToolTipText("Borrar");
		ventaBoton.setToolTipText("Guardar Venta");

		
		
		config.gridx=5;
		config.gridy=0;
		config.gridwidth=1; //Para ocupar dos huecos en el eje x
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		this.add(fecha, config);

		config.gridx=0;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(codigo, config);

		config.gridx=1;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(producto, config);

		config.gridx=2;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(cantidad, config);

		config.gridx=3;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(precio, config);

		config.gridx=4;
		config.gridy=2;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(stock, config);

		config.gridx=0;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(cliente, config);

		config.gridx=1;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(nombre, config);

		config.gridx=5;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(totalAPagar, config);
		config.gridx=5;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(dineroParaPagar, config);

		config.gridx=3;
		config.gridy=0;
		config.gridwidth=2;
		config.gridheight=2;
		config.weightx = 0.0;
		config.weighty = 0.0;
		config.fill = GridBagConstraints.EAST;
		this.add(detalleBoton,config);

		config.gridx=4;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=2;
		config.fill = GridBagConstraints.WEST;
		this.add(ventaBoton, config);

		config.gridx=5;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(0, 5, 5, 5);
		config.fill = GridBagConstraints.CENTER; //Para ocupar el centro
		this.add(borrarBoton, config);

		config.gridx=3;
		config.gridy=5;
		config.gridwidth=1;
		config.gridheight=2;
		this.add(printBoton, config);

		config.gridx=5;
		config.gridy=1;
		config.gridwidth=1;
		config.gridheight=1;
		config.fill = GridBagConstraints.BOTH;
		config.ipadx = 40; //Tamaño x
		config.insets = new Insets(4, 5, 5, 5);

		this.add(fechaTexto, config);
		config.gridx=0;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;

		this.add(codigoTexto, config);
		config.gridx=1;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;

		this.add(productoTexto, config);
		config.gridx=2;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(cantidadTexto, config);
		config.gridx=3;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(precioTexto, config);
		config.gridx=4;
		config.gridy=3;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(stockTexto, config);
		config.gridx=0;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(0, 5, 5, 5);
		this.add(clienteTexto, config);
		config.gridx=1;
		config.gridy=6;
		config.gridwidth=1;
		config.gridheight=1;
		this.add(nombreTexto, config);

		config.gridx=0;
		config.gridy=4;
		config.gridwidth=6;
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.ipady = 260; //Tamaño y
		this.add(tabla, config);


		codigoTexto.addActionListener(new ActionListener() { //Para cuando pulse Enter en el campo de codigoTexto
			public void actionPerformed(ActionEvent e) {
				String codigoIngresado = codigoTexto.getText();
				System.out.println(codigoIngresado);

				Conexion conexion = new Conexion();
				Connection cn = null;
				String[][] datos = null;

				try {
					cn = conexion.conectar();

					String[][] codigoProducto = conexion.getData("SELECT codigo FROM producto");

					if (!codigoIngresado.isEmpty() && validacion.validarPalabraEnMatrizString(codigoProducto, codigoIngresado)) {
						datos = conexion.getData("SELECT nombre, precio, stock FROM producto WHERE codigo = '" + codigoIngresado + "'");
						System.out.println(datos);
						productoTexto.setText(datos[0][0]);
						precioTexto.setText(datos[0][1]);
						stockTexto.setText(datos[0][2]);
					} else {
						datosFrame1.setVisible(true);
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});


		clienteTexto.addActionListener(new ActionListener() { //Para cuando pulse Enter en el campo de clienteTexto
			public void actionPerformed(ActionEvent e) {
				String clienteIngresado = clienteTexto.getText();
				System.out.println(clienteIngresado);

				Conexion conexion = new Conexion();
				Connection cn = null;
				String[][] datos = null;

				try {
					cn = conexion.conectar();

					String[][] clientesID = conexion.getData("SELECT id FROM cliente");

					if (!clienteIngresado.isEmpty() && validacion.validarPalabraEnMatrizString(clientesID, clienteIngresado)) {
						datos = conexion.getData("SELECT nombre FROM cliente WHERE id = " + clienteIngresado);
						System.out.println(datos);
						nombreTexto.setText(datos[0][0]);
					} else {
						datosFrame2.setVisible(true); //Crear una ventana nueva
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});


		nombreTexto.addActionListener(new ActionListener() { //Para cuando pulse Enter en el campo de nombreTexto
			public void actionPerformed(ActionEvent e) {
				String nombreIngresado = nombreTexto.getText();
				System.out.println(nombreIngresado);

				Conexion conexion = new Conexion();
				Connection cn = null;
				String[][] datos = null;

				try {
					cn = conexion.conectar();

					String[][] clientesNombre = conexion.getData("SELECT nombre FROM cliente");

					if (!nombreIngresado.isEmpty() && validacion.validarPalabraEnMatrizString(clientesNombre, nombreIngresado)) {
						datos = conexion.getData("SELECT id FROM cliente WHERE nombre = '" + nombreIngresado + "'");
						System.out.println(datos);
						clienteTexto.setText(datos[0][0]);
					} else {
						datosFrame3.setVisible(true); //Crear una ventana nueva
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});


		detalleBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] nuevosDatos = panelTabla.datosFiltradosColumnas("detalle", "id, nombreProducto, cantidad, precioProducto, total", condicion);
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos

				String productoT = productoTexto.getText();

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();
					

					String[][] nombresProductos = conexion.getData("SELECT nombre FROM producto");

					if (!productoT.isEmpty() && validacion.validarPalabraEnMatrizString(nombresProductos, productoT) && validacion.esEntero(cantidadTexto.getText()) == true && validacion.esNumeroFloat(precioTexto.getText()) == true) {
						float cantidadT = Integer.parseInt(cantidadTexto.getText());
						float precioT = Float.parseFloat(precioTexto.getText());
						float total = cantidadT * precioT;
						String valores = "'" + productoT + "', " + cantidadT + ", " + precioT + ", " + total + ", " + idVenta;
						int stock = Integer.parseInt(conexion.getONEData("SELECT stock FROM producto WHERE nombre = '" + productoT + "'"));
						if (cantidadT <= stock && cantidadT > 0) {
							System.out.println("La cantidad esta bien");
							conexion.hacerConsultaNoReturn("UPDATE producto SET stock = " + (stock - cantidadT) + " WHERE nombre = '" + productoT + "'"); 
							conexion.insertData("detalle (nombreProducto, cantidad, precioProducto, total, id_venta)", valores);
							dineroParaPagar.setText(conexion.getONEData("SELECT SUM(total) FROM detalle WHERE id_venta = " + idVenta));
							stockTexto.setText(conexion.getONEData("SELECT stock FROM producto WHERE nombre = '" + productoT + "'"));
						} else {
							cantidadFrame.setVisible(true);
							System.out.println("La cantidad esta mal");
						}
					} else {
						String mensaje = "Estos datos son incorrectos:";
						//".isEmpty()", para saber si no esta vacío
						if (productoT.isEmpty() && !validacion.validarPalabraEnMatrizString(nombresProductos, productoT)) {
							mensaje = mensaje + " Producto(Es el Nombre)";
						}
						if (!validacion.esEntero(cantidadTexto.getText()) == true) {
							mensaje = mensaje + " Cantidad";
						}
						if (!validacion.esNumeroFloat(precioTexto.getText()) == true) {
							mensaje = mensaje + " Precio";
						}
						datosFrame = new DatosIncorrectos(mensaje);
						datosFrame.setVisible(true);
					}	
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}

				nuevosDatos = panelTabla.datosFiltradosColumnas("detalle", "id, nombreProducto, cantidad, precioProducto, total", condicion);
				tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos

			}
		});


		ventaBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clienteT = clienteTexto.getText();

				String fechaT = fechaTexto.getText();

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();
					String[][] clientesIDTabla = conexion.getData("SELECT id FROM cliente");
					if (validacion.validarFecha(fechaT) == true && !clienteT.isEmpty() && validacion.validarPalabraEnMatrizString(clientesIDTabla, clienteT)) {


						String suma = conexion.getONEData("SELECT SUM(total) FROM detalle WHERE id_venta = " + idVenta);
						String valores = "'" + clienteT + "', " + suma + ", '" + fechaT + "'";

						conexion.insertData("venta (cliente, total, fecha)", valores);

						idVenta = idVenta + 1;
						condicion = " WHERE id_venta = " + idVenta;

						String[][] nuevosDatos = panelTabla.datosFiltradosColumnas("detalle", "id, nombreProducto, cantidad, precioProducto, total", condicion);
						tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
						
						dineroParaPagar.setText("----€"); //Reinicializamos el dinero ya que la venta se a terminado
						
						//Ponemos todos los campos de textos vacíos
						productoTexto.setText("");
						precioTexto.setText("");
						stockTexto.setText("");
						codigoTexto.setText("");
						cantidadTexto.setText("");
						clienteTexto.setText("");
						nombreTexto.setText("");
						
						guardado.setVisible(true);
					} else {
						String mensaje = "Estos datos son incorrectos:";
						if (!validacion.validarFecha(fechaT) == true) {
							mensaje = mensaje + " Fecha";
						}
						if (clienteT.isEmpty() && !validacion.validarPalabraEnMatrizString(clientesIDTabla, clienteT)) {
							mensaje = mensaje + " Cliente(Es el ID)";
						}
						datosFrame = new DatosIncorrectos(mensaje);
						datosFrame.setVisible(true);
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});


		borrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar frame = new Borrar("detalle");
				frame.setVisible(true);
			}
		}
				);


		printBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarHTML html = new ExportarHTML();
				String nombreArchivo = "detalleVenta" + idVenta;

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();

					html.exportarHTML(panelTabla.datosFiltradosColumnas("detalle", "id, nombreProducto, cantidad, precioProducto, total", condicion), cabezera, nombreArchivo, conexion.getONEData("SELECT nombre FROM configuracion"), conexion.getONEData("SELECT telefono FROM configuracion"), conexion.getONEData("SELECT direccion FROM configuracion"));

					abrirHTML.abrirHTMLenNavegador(nombreArchivo);
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		}
				);
	}
}