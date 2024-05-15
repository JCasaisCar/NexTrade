package vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import validaciones.Validaciones;

public class Bienvenida extends JFrame {

	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Declaración de el JPanel
	JPanelBienvenida panel;
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();
	
	//Declaración de el icono
	private ImageIcon iconoNexTrade = new ImageIcon(Sistema.class.getResource("/img/logoRedondo.png"));

	public Bienvenida(String nombre) {
		this.setTitle("Bienvenida NexTrade");
		this.setSize(350, 210); //Alto y ancho de la ventana
		this.setResizable(false); //Para que no se pueda redimensionar
		this.setLocationRelativeTo(null); //Para poner la ventana en el centro de la pantalla
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Para desactivar el cierre del programa cuando le damos a cerrar
		this.setLayout(layout); //Para poner el gridLayout
		this.setIconImage(iconoNexTrade.getImage()); //Icono de el JFrame


		//Crear panel y pasar el parámetro
		panel = new JPanelBienvenida(nombre);


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
		
		
		//Ponemos un WindowListener con el método windowClosing para que cuando el usuario pulse la x de cerrar el programa haga algo que le ponemos nosotros
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                validacion.confirmarSalida(Bienvenida.this); //Método para mostrar la confirmación para salir del programa
            }
        });
	}
}