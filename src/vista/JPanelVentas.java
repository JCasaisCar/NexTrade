package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbdd.Conexion;
import html.AbrirHTML;
import html.ExportarHTML;
import validaciones.Validaciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelVentas extends JPanel{
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	//Creación tabla
	private String[] cabezera = {"ID", "CLIENTE", "TOTAL", "FECHA"};

	//Declaración tabla
	private JPanelTabla panelTabla = new JPanelTabla();
	private JScrollPane tabla = panelTabla.getTabla(cabezera, panelTabla.datos("venta"));

	//Declaración iconos de los botones
	private ImageIcon iconoLupa = new ImageIcon(Sistema.class.getResource("/img/lupa.png"));
	private ImageIcon iconoPrint = new ImageIcon(Sistema.class.getResource("/img/print.png"));

	//Declaración botones
	private JButton lupaBoton = new JButton(iconoLupa);
	private JButton printBoton = new JButton(iconoPrint);

	//Texto de la lupa
	private JTextField lupaTexto = new JTextField(20);

	//Declaración color 
	private Color color = new Color(255, 215, 0);

	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	private DatosIncorrectos datosFrame = new DatosIncorrectos("El ID escrito es incorrecto o esta vacio");
	private AbrirHTML abrirHTML = new AbrirHTML();

	public JPanelVentas(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);
		
		
		lupaBoton.setToolTipText("Buscar o Actualizar"); //"setToolTipText", para poner un texto alternativo cuando el raton pasa por encima
		printBoton.setToolTipText("Imprimir");


		config.gridx=0;
		config.gridy=0;
		config.gridwidth=1; //Para ocupar dos huecos en el eje x
		config.gridheight=1;
		config.weightx = 1.0; //Ocupa todo el ancho disponible
		config.weighty = 1.0; //Ocupa todo el alto disponible
		config.fill = GridBagConstraints.BOTH; //Para ocupar todo el espacio disponible
		config.insets = new Insets(22, 450, 22, 10); //Para poner margenes
		this.add(lupaBoton, config);

		config.gridx=2;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=1;
		config.insets = new Insets(22, 10, 22, 450);
		this.add(printBoton, config);

		config.gridx=1;
		config.gridy=0;
		config.gridwidth=1;
		config.gridheight=1;
		config.ipadx = 200; //Tamaño x
		config.insets = new Insets(30, 0, 30, 0);
		this.add(lupaTexto, config);

		config.gridx=0;
		config.gridy=1;
		config.gridwidth=3;
		config.gridheight=1;
		config.ipady = 400; //Tamaño y
		config.insets = new Insets(5, 5, 5, 5);
		this.add(tabla, config);


		lupaBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idT = lupaTexto.getText();
				String[][] nuevosDatos;

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();

					String[][] nDatos = conexion.getData("SELECT id FROM venta");

					if (!idT.isEmpty() && validacion.validarPalabraEnMatrizString(nDatos, idT)) { //Para verificar si el texto no está vacío y esta en la tabla venta
						nuevosDatos = panelTabla.datosFiltradosID("venta", idT);
					} else {
						nuevosDatos = panelTabla.datos("venta");
					}
					tabla.setViewportView(panelTabla.getTabla(cabezera, nuevosDatos)); //Para actualizar la tabla con datos nuevos
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});


		printBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarHTML html = new ExportarHTML();
				String nombreArchivo = "ventas";
				String idT = lupaTexto.getText();

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();

					String[][] nDatos = conexion.getData("SELECT id FROM venta");

					//".isEmpty()", para saber si no esta vacío
					if (!idT.isEmpty() && validacion.validarPalabraEnMatrizString(nDatos, idT)) { //Para verificar si el texto no está vacío y esta en la tabla venta
						html.exportarHTML(panelTabla.datosFiltrados("SELECT * FROM venta WHERE id = " + idT), cabezera, nombreArchivo, conexion.getONEData("SELECT nombre FROM configuracion"), conexion.getONEData("SELECT telefono FROM configuracion"), conexion.getONEData("SELECT direccion FROM configuracion"));
					} else {
						html.exportarHTML(panelTabla.datos("venta"), cabezera, nombreArchivo, conexion.getONEData("SELECT nombre FROM configuracion"), conexion.getONEData("SELECT telefono FROM configuracion"), conexion.getONEData("SELECT direccion FROM configuracion"));
					}

					abrirHTML.abrirHTMLenNavegador(nombreArchivo);
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		}
				);
	}
}