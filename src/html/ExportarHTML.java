package html;

import java.io.FileWriter;
import java.io.IOException;

public class ExportarHTML {

	public static void exportarHTML(String[][] datos, String[] cabecera, String nombreArchivo, String nombreEmpresa, String telefonoEmpresa, String direccionEmpresa) {
		try {
			FileWriter archivo = new FileWriter(nombreArchivo + ".html");

			StringBuilder contenidoHTML = new StringBuilder();
			contenidoHTML.append("<html><body>");

			//Agregamos información de la empresa
			contenidoHTML.append("<h1>").append(nombreEmpresa).append("</h1>");
			contenidoHTML.append("<p>Teléfono: ").append(telefonoEmpresa).append("</p>");
			contenidoHTML.append("<p>Dirección: ").append(direccionEmpresa).append("</p>");

			contenidoHTML.append("<table border='1'>");

			//Agregamos la cabecera
			contenidoHTML.append("<tr>");
			for (String titulo : cabecera) {
				contenidoHTML.append("<th>").append(titulo).append("</th>");
			}
			contenidoHTML.append("</tr>");

			//Agregamos los datos
			for (String[] fila : datos) {
				contenidoHTML.append("<tr>");
				for (String dato : fila) {
					contenidoHTML.append("<td>").append(dato).append("</td>");
				}
				contenidoHTML.append("</tr>");
			}

			contenidoHTML.append("</table></body></html>");

			archivo.write(contenidoHTML.toString());
			archivo.close();

			System.out.println("¡Datos exportados a HTML con éxito!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}