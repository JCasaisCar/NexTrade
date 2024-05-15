package validaciones;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Validaciones {

	public boolean validarDNI(String dni) {
		if (dni == null || dni.length() != 9) {
			return false;
		}
		String numero = dni.substring(0, 8); //Para coger solo los números
		char letra = Character.toUpperCase(dni.charAt(8)); //Para transformar el caracter 8 que es la letra a mayúsculas

		if (!numero.matches("[0-9]{8}")) { //".matches", para saber si coincide o no con la expresión regular
			return false;
		}
		int numDNI = Integer.parseInt(numero);
		int resto = numDNI % 23; //"%", para coger el resto

		final String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

		return letra == letrasDNI.charAt(resto); //"letrasDNI.charAt(resto)", para saber la letra del índice del resto
	}


	public boolean esNumeroFloat(String numero) {
		//"numero.isEmpty()", para saber si no esta vacío
		if (numero == null || numero.isEmpty()) {
			return false;
		}
		try {
			Float.parseFloat(numero); //Si se puede parsear a float es un numero con decimales
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	public boolean esEntero(String numero) {
		try {
			Integer.parseInt(numero); //Si se puede parsear a int es un número entero
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	public boolean validarNombre(String nombre) {
		//Expresión regular para comprobar si el nombre es correcto
		String expr = "[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]+";
		return nombre != null && nombre.matches(expr);
	}


	public boolean validarNumeroTelefono(String numero) {
		if (numero == null || numero.isEmpty()) {
			return false;
		}
		//Expresión regular para comprobar si el teléfono es correcto
		String expr = "[6-9][0-9]{8}";
		return numero.matches(expr);
	}


	public boolean validarDireccion(String direccion) {
		//Expresión regular para comprobar si la dirección es correcta
		String expr = "^[a-zA-Z0-9, ]+$";
		return direccion.matches(expr);
	}


	public boolean validarFecha(String fecha) {
		//Expresión regular para comprobar si la la fecha es correcta
		String expr = "^\\d{2}/\\d{2}/\\d{4}$";
		return fecha.matches(expr);
	}


	public boolean validarPalabraEnMatrizString(String[][] matriz, String palabra) {
		for (String[] fila : matriz) {
			for (String elemento : fila) {
				if (elemento.equals(palabra)) {
					return true; //Si se encuentra la palabra, devuelve true
				}
			}
		}
		return false; //Si no se encuentra la palabra, devuelve false
	}
	
	
	
    public void confirmarSalida(JFrame frame) {
    	//Para mostrar el diálogo de confirmación a la hora de querer salir del programa
    	//"UIManager.put", para cambiar el texto a los botones
    	UIManager.put("OptionPane.yesButtonText", "Sí"); //Ponemos "("OptionPane.yesButtonText", "Sí")" para cambiar el boton de yes a sí
        UIManager.put("OptionPane.noButtonText", "No");
    	
    	//Ponemos la opción en un int debido a que "JOptionPane.showConfirmDialog" devuelve un 0 o un 1 que son numeros enteros
        int option = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea salir?", "Confirmar salida NexTrade", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { //Si devuelve un 1 que es lo mismo que "JOptionPane.YES_OPTION" cerramos el programa con "frame.dispose();"
            frame.dispose(); //Para cerrar el JFrame
            System.out.println("SE HA FINALIZADO EL PROGRAMA");
        }
    }
}