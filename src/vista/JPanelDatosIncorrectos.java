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

public class JPanelDatosIncorrectos extends JPanel{
	private Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	private GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	private GridBagConstraints config = new GridBagConstraints();

	//Textos y frases
	private JLabel datos = new JLabel("REVISA LOS DATOS");
	private JLabel datosTexto;

	//Declaración boton
	private JButton okBoton = new JButton("OK");

	//Declaración logo
	private JPanelLogo logo = new JPanelLogo();

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();

	public JPanelDatosIncorrectos(String mensaje){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		datos.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente


		datosTexto = new JLabel(mensaje);


		config.gridx=0;
		config.gridy=0;
		config.insets = new Insets(5, 5, 5, 5); //Para poner margenes
		this.add(datos, config);
		config.gridx=0;
		config.gridy=1;
		this.add(datosTexto, config);

		config.gridx=0;
		config.gridy=2;
		this.add(okBoton, config);


		okBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(JPanelDatosIncorrectos.this); //Para obtener el JFrame que contiene este JPanel para después poder cerrarla
				window.dispose();
			}
		});
	}
}