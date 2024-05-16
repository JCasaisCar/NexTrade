package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbdd.Conexion;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanelBorrar extends JPanel{
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	//Declaración textos
	private JLabel idBorrar = new JLabel("ESCRIBE EL ID:");
	private JTextField idBorrarTexto = new JTextField(20);

	//Declaración botones
	private JButton borrarBoton = new JButton("Borrar");
	private JButton salirBoton = new JButton("Salir");

	//Declaración logo
	private JPanelLogo logo = new JPanelLogo();

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	private DatosIncorrectos datosFrame = new DatosIncorrectos("El ID escrito es incorrecto o esta vacio");

	public JPanelBorrar(String tablaBorrar){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		idBorrar.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente


		config.gridx=0;
		config.gridy=0;
		config.insets = new Insets(5, 5, 5, 5); //Para poner margenes
		this.add(idBorrar, config);
		config.gridx=0;
		config.gridy=1;
		this.add(idBorrarTexto, config);

		config.gridx=0;
		config.gridy=2;
		this.add(borrarBoton, config);

		config.gridx=0;
		config.gridy=3;
		this.add(salirBoton, config);


		borrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idBorrarT = idBorrarTexto.getText();

				Conexion conexion = new Conexion();
				Connection cn = null;

				try {
					cn = conexion.conectar();

					String[][] nDatos = conexion.getData("SELECT id FROM " + tablaBorrar);

					if (validacion.validarPalabraEnMatrizString(nDatos, idBorrarT)) {
						System.out.println("Entra");
						conexion.deleteData(tablaBorrar, idBorrarT);
						Window window = SwingUtilities.getWindowAncestor(JPanelBorrar.this); //Para obtener el JFrame que contiene este JPanel para después poder cerrarla
						window.dispose();
					} else {
						System.out.println("No ha entrado");
						datosFrame.setVisible(true);
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				} 
			}
		});


		salirBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(JPanelBorrar.this);
				window.dispose();

			}
		});
	}
}