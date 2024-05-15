package vista;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import validaciones.Validaciones;

public class Sistema extends JFrame {
	//Declaración de el grid
	GridBagLayout layout = new GridBagLayout();
	//Declaración variable para aplicar la configuración al grid
	GridBagConstraints config = new GridBagConstraints();

	//Declaración de los JPanel
	JPanelNuevaVenta panel1 = new JPanelNuevaVenta();
	JPanelClientes panel2 = new JPanelClientes();
	JPanelProveedor panel3 = new JPanelProveedor();
	JPanelProductos panel4 = new JPanelProductos();
	JPanelVentas panel5 = new JPanelVentas();
	JPanelConfiguracion panel6 = new JPanelConfiguracion();

	//Declaración de los iconos
	ImageIcon iconoNuevaVenta = new ImageIcon(Sistema.class.getResource("/img/Nventa.png"));
	ImageIcon iconoClientes = new ImageIcon(Sistema.class.getResource("/img/Clientes.png"));
	ImageIcon iconoProveedor = new ImageIcon(Sistema.class.getResource("/img/proveedor.png"));
	ImageIcon iconoProductos = new ImageIcon(Sistema.class.getResource("/img/producto.png"));
	ImageIcon iconoVentas = new ImageIcon(Sistema.class.getResource("/img/Carrito-de-compras.png"));
	ImageIcon iconoConfiguración = new ImageIcon(Sistema.class.getResource("/img/config.png"));

	//Declaración de los botones
	JButton boton1 = new JButton("NuevaVenta", iconoNuevaVenta);
	JButton boton2 = new JButton("Clientes", iconoClientes);
	JButton boton3 = new JButton("Proveedor", iconoProveedor);
	JButton boton4 = new JButton("Productos", iconoProductos);
	JButton boton5 = new JButton("Ventas", iconoVentas);
	JButton boton6 = new JButton("Configuración", iconoConfiguración);

	//Declaración de el texto
	JLabel texto = new JLabel("NexTrade: Sistema de Ventas");
	
	//Declaración de la fuente 
	Font fuente = new Font("Tahoma", Font.BOLD, 80);
	
	//Declaración de el logo
	JPanelLogo logo = new JPanelLogo();

	//Declaración color 
	private Color color = new Color(255, 215, 0);
	
	//Declaración de el objeto
	private Validaciones validacion = new Validaciones();
	
	//Declaración logo
	private ImageIcon iconoNexTrade = new ImageIcon(Sistema.class.getResource("/img/logoRedondo.png"));

	public Sistema() {
		this.setTitle("NexTrade");
		this.setSize(1500, 760); //Alto y ancho de la ventana
		this.setResizable(false); //Para que no se pueda redimensionar
		this.setLocationRelativeTo(null); //Para poner la ventana en el centro de la pantalla
		this.setLayout(layout); //Para poner el gridLayout
		getContentPane().setBackground(color);  //Para poner el color de fondo
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Para desactivar el cierre del programa cuando le damos a cerrar
		this.setIconImage(iconoNexTrade.getImage()); //Icono de el JFrame

		
		//Configuración de peso y relleno para los componentes
		config.weightx = 1.0;
		config.weighty = 1.0;
		config.fill = GridBagConstraints.BOTH;
		config.insets = new Insets(5, 5, 5, 5); //Margen

		//Configuración paneles
		config.gridx = 1;
		config.gridy = 1;
		config.gridwidth = 1;
		config.gridheight = 6;
		//Agregar los paneles
		this.add(panel1, config);
		this.add(panel2, config);
		this.add(panel3, config);
		this.add(panel4, config);
		this.add(panel5, config);
		this.add(panel6, config);

		//Configurar botones y texto
		configurarBoton(boton1, 0, 1);
		configurarBoton(boton2, 0, 2);
		configurarBoton(boton3, 0, 3);
		configurarBoton(boton4, 0, 4);
		configurarBoton(boton5, 0, 5);
		configurarBoton(boton6, 0, 6);

		//Configuración texto
		config.gridx = 1;
		config.gridy = 0;
		config.gridwidth = 1;
		config.gridheight = 1;
		config.weightx = 0.0; //Para que no se expanda en el eje x
		config.weighty = 0.0; //Para que no se expanda en el eje y
		config.anchor = GridBagConstraints.WEST; //Para alinearlo a la izquierda de la celda
		config.fill = GridBagConstraints.HORIZONTAL; //Para expandirlo horizontalmente para llenar todo el espacio horizontal disponible en la celda
		config.insets = new Insets(14, 40, 14, 5); //Para poner margenes
		this.add(texto, config);
		texto.setFont(fuente);

		//Configuración imagen logo
		config.gridx = 0;
		config.gridy = 0;
		config.ipady = 160;
		config.insets = new Insets(14, 5, 14, 5);
		this.add(logo, config);


		//Inicialmente mostrar solo el primer panel
		panel1.setVisible(true);
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		panel5.setVisible(false);
		panel6.setVisible(false);

		//Configuración eventos botones
		configurarEventoBoton(boton1, panel1);
		configurarEventoBoton(boton2, panel2);
		configurarEventoBoton(boton3, panel3);
		configurarEventoBoton(boton4, panel4);
		configurarEventoBoton(boton5, panel5);
		configurarEventoBoton(boton6, panel6);
		
		
		//Ponemos un WindowListener con el método windowClosing para que cuando el usuario pulse la x de cerrar el programa haga algo que le ponemos nosotros
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                validacion.confirmarSalida(Sistema.this); //Método para mostrar la confirmación para salir del programa
            }
        });
	}


	private void configurarBoton(JButton boton, int x, int y) {
		config.gridx = x;
		config.gridy = y;
		config.gridwidth = 1; //Para que ocupe solo una celda en el eje x
		config.gridheight = 1;
		config.ipadx = 30; //Tamaño x
		config.ipady = 10; //Tamaño y
		config.weightx = 0.0; //Para que no se expandan en el eje x
		config.weighty = 0.0;
		config.anchor = GridBagConstraints.WEST; //Para alinearlo a la izquierda de la celda
		config.fill = GridBagConstraints.HORIZONTAL; //Para expandirlo horizontalmente para llenar todo el espacio horizontal disponible en la celda
		if(y == 1) {
			config.insets = new Insets(5, 5, 14, 5);
		} else {
			config.insets = new Insets(14, 5, 14, 5); //Margen
		};
		this.add(boton, config); //Agregar boton
	}


	private void configurarEventoBoton(JButton boton, JPanel panel) {
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Ocultar todos los paneles
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				//Mostrar el panel correspondiente al botón
				panel.setVisible(true);
			}
		});
	}
}