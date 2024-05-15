package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

public class JPanelAcceso extends JPanel{
	//Declaración de la fuente personalizada 
	Font fuenteChica = new Font("Comic Sans MS", Font.ITALIC, 20);

	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Declaración de textos y frases
	private JLabel correo = new JLabel("Correo Electrónico:");
	private JTextField correoTexto = new JTextField(20);
	private JLabel contrasenia = new JLabel("Contraseña:");
	private JPasswordField contraseniaTexto = new JPasswordField(20);

	//Declaración boton
	private JButton entrarBoton = new JButton("Entrar");
	private JButton verContraseniaBoton = new JButton("Ver");
	
	//Declaración logo
	JPanelLogo logo = new JPanelLogo();

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	//Declaración de el objeto

	private Validaciones validacion = new Validaciones();

	//Declaración de los JFrame
	DatosIncorrectos datosFrame;
	Bienvenida bienvenidaFrame;


	public JPanelAcceso(){
		this.setLayout(layout); //Para poner el gridLayout
		this.setBackground(color);


		correo.setFont(fuenteChica); //Para poner la fuente que hemos creado previamente
		contrasenia.setFont(fuenteChica);


		config.gridx=0;
		config.gridy=1;
		config.insets = new Insets(5, 5, 5, 5); //Para poner margenes
		config.gridwidth = 2; //Para que ocupe 2 huecos del grid en el eje x
		this.add(correo, config);
		config.gridx=0;
		config.gridy=2;
		this.add(correoTexto, config);

		config.gridx=0;
		config.gridy=3;
		config.gridwidth = 1;
		this.add(contrasenia, config);
		
		config.gridx=1;
		config.gridy=3;
		this.add(verContraseniaBoton, config);
		
		
		config.gridx=0;
		config.gridy=4;
		config.gridwidth = 2;
		this.add(contraseniaTexto, config);

		config.gridx=0;
		config.gridy=5;
		this.add(entrarBoton, config);

		//Configuración imagen logo
		config.gridx = 0;
		config.gridy = 0;
		config.ipadx = 160; //Para agregar un espacio adicional de 160 píxeles en el eje x
		config.ipady = 160;
		this.add(logo, config);


		entrarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String correoT = correoTexto.getText(); //Para sacar el texto del correo
				String contraseniaT = contraseniaTexto.getText();

				Conexion conexion = new Conexion();
				Connection cn = null;
				String[][] datos = null;

				try {
					cn = conexion.conectar();

					String[][] correos = conexion.getData("SELECT correo FROM usuario");
					String[][] contrasenias = conexion.getData("SELECT contrasenia FROM usuario");

					String contraseniaDeCorreo = conexion.getONEData("SELECT contrasenia FROM usuario WHERE correo = '" + correoT + "'");

					//".isEmpty()", para saber si no esta vacio
					if (!correoT.isEmpty() && validacion.validarPalabraEnMatrizString(correos, correoT) && !contraseniaT.isEmpty() && validacion.validarPalabraEnMatrizString(contrasenias, contraseniaT) && contraseniaDeCorreo.equals(contraseniaT)) {
						System.out.println("Ha entrado");
						bienvenidaFrame = new Bienvenida(conexion.getONEData("SELECT nombre FROM usuario WHERE correo = '" + correoT + "'"));
						bienvenidaFrame.setVisible(true);
						Window window = SwingUtilities.getWindowAncestor(JPanelAcceso.this); //Para obtener el JFrame que contiene este JPanel para después poder cerrarla 
						window.dispose();
					} else {
						System.out.println("No ha entrado");
						String mensaje = "Estos datos son incorrectos:";
						boolean correcto1 = true;
						boolean correcto2 = true;
						
						if (!validacion.validarPalabraEnMatrizString(correos, correoT)) {
							mensaje = mensaje + " Correo";
							correcto1 = false;
						} else {
							correcto1 = true;
						}
						
						if (!validacion.validarPalabraEnMatrizString(contrasenias, contraseniaT)) {
							mensaje = mensaje + " Contraseña";
							correcto2 = false;
						} else {
							correcto2 = true;
						}
						
						if (correcto1 && correcto2 && !contraseniaDeCorreo.equals(contraseniaT)) {
							mensaje = "El correo y la contraseña son correctos pero no coinciden";
						}
						datosFrame = new DatosIncorrectos(mensaje);
						datosFrame.setVisible(true);
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				} 
			}
		});
		
		
		verContraseniaBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verContraseniaBoton.getText().equals("Ver")) {
					contraseniaTexto.setEchoChar((char) 0); //Mostrar contraseña (caracteres visibles)
		            verContraseniaBoton.setText("Ocultar");
		        } else {
		        	contraseniaTexto.setEchoChar('\u2022'); //Ocultar contraseña (caracteres ocultos)
		            verContraseniaBoton.setText("Ver");
		        }
			}
		});
		
	}
}