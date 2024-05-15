package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelLogo extends JPanel{
	@Override
	public void paint(Graphics g){
		Dimension dimension = this.getSize();
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/logoRedondo.png"));
		//El primer parámetro sera nuestro objeto de tipo ImageIcon, contiene la imagen que vamos a agregar.
		//El segundo y tercer parámetro serán dos valores enteros que equivalen a la posición en el eje x,y.
		//El cuarto y quinto parámetro serán las dimensiones que tendrá nuestra imagen, para este ejemplo las dimensiones sera iguales a las dimensiones de nuestro JPanel de esta manera garantizamos que la imagen tenga el mismo tamaño de nuestra ventana JFrame.
		//El ultimo parámetro solamente pasamos un valor null
		g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
		setOpaque(false); //Para que no sea opaco
		super.paintChildren(g); //Para asegurar que los elementos secundarios se pinten correctamente
	}
}