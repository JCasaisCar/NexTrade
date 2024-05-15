package vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatosIncorrectos extends JFrame {

	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Declaración de el JPanel
	JPanelDatosIncorrectos panel;
	
	private ImageIcon iconoNexTrade = new ImageIcon(Sistema.class.getResource("/img/logoRedondo.png"));

	public DatosIncorrectos(String mensaje) {
		this.setTitle("Error NexTrade");
		this.setSize(500, 210); // Alto y ancho de la ventana
		this.setResizable(false); //Para que no se pueda redimensionar
		this.setLocationRelativeTo(null); //Para poner la ventana en el centro de la pantalla
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Para desactivar el cierre del programa cuando le damos a cerrar
		this.setLayout(layout); //Para poner el gridLayout
		this.setIconImage(iconoNexTrade.getImage()); //Icono de el JFrame

		
		//Lo inicializamos ahora para poder pasarle el mensaje
		panel = new JPanelDatosIncorrectos(mensaje);


		//Configuración panel
		config.gridx = 0;
		config.gridy = 0;
		config.gridwidth = 1;
		config.gridheight = 6; //Para que ocupe 6 huecos del grid en el eje y
		config.weightx = 1.0; //Para que ocupe todo el espacio del hueco en el eje x
		config.weighty = 1.0;
		config.fill = GridBagConstraints.BOTH; //Para que ocupe todo el espacio disponible en el hueco
		//Agregar panel
		this.add(panel, config);


		//Inicialmente mostramos el panel
		panel.setVisible(true);     
	}
}