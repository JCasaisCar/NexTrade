package vista;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bbdd.Conexion;

public class JPanelTabla {

	public JScrollPane getTabla(String[] cabezera, String[][] datos) {
		DefaultTableModel model = new DefaultTableModel(datos, cabezera) {
			//Método para que no sea editable los campos de la tabla
			@Override
			public boolean isCellEditable(int row, int column) {
				//Devuelve false para todas las celdas para que no sea editable
				return false;
			}
		};

		JTable tabla = new JTable(model);
		JScrollPane scroll = new JScrollPane(tabla);

		return scroll;
	}


	public String[][] datos(String tabla){
		Conexion conexion = new Conexion();
		Connection cn = null;
		String[][] datos = null;

		try {
			cn = conexion.conectar();

			datos = conexion.getAllData(tabla);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return datos;
	}


	public String[][] datosFiltrados(String consulta){
		Conexion conexion = new Conexion();
		Connection cn = null;
		String[][] datos = null;

		try {
			cn = conexion.conectar();

			datos = conexion.getData(consulta);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return datos;
	}


	public String[][] datosFiltradosColumnas(String tabla, String columnas, String adiccion){
		Conexion conexion = new Conexion();
		Connection cn = null;
		String[][] datos = null;

		try {
			cn = conexion.conectar();

			datos = conexion.getData("SELECT " + columnas + " FROM " + tabla + adiccion);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return datos;
	}


	public String[][] datosFiltradosID(String tabla, String id){
		Conexion conexion = new Conexion();
		Connection cn = null;
		String[][] datos = null;

		try {
			cn = conexion.conectar();

			datos = conexion.getData("SELECT * FROM " + tabla + " WHERE id = " + id);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return datos;
	}
}