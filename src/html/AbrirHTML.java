package html;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AbrirHTML {

	public void abrirHTMLenNavegador(String nombreArchivo) {
		File archivoHTML = new File(nombreArchivo + ".html");
		try {
			if (Desktop.isDesktopSupported()) { //Para verificar si la clase Desktop está soportada en el ordenador
				Desktop.getDesktop().browse(archivoHTML.toURI()); //Para abrir el archivo en el navegador web predeterminado del ordenador
			} else {
				System.out.println("El escritorio no es compatible.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}