package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;

public class Conexion {

	//Driver JDBC
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	//Dirección de la BBDD MySQL
	private static final String URL = "jdbc:mysql://localhost:3306/nextrade";
	//Usuario y contraseña de acceso a la BD
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";

	public Connection conectar() {
		Connection conexion = null;

		try {
			Class.forName(DRIVER); //Para que sepa en que clase se encuentra el driver para conectar la BD
			conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			System.out.println("Conexión OK");

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}

	public void cerrarConexion(Connection conection){
		try {
			//Cierre de la conexión
			conection.close();
		} catch (SQLException e) {
			System.err.println("Se ha producido un error al cerrar la conexión");

		}
	}


	public void hacerConsultaNoReturn(String consulta) throws SQLException{
		Connection conexion = conectar();
		try {

			//Datos a insertar
			System.out.println(consulta);
			//Creación del Statement para poder realizar la consulta
			Statement consul = conexion.createStatement();
			//Ejecución de la consulta
			consul.executeUpdate(consulta);
			System.out.println("Datos insertados correctamente");
			//Cierre del Statement
			consul.close();
		}finally {
			//Cierre de la conexión
			cerrarConexion(conexion);
		}
	}



	public void insertData(String tabla, String valores) throws SQLException{
		Connection conexion = conectar();
		try {

			//Datos a insertar
			String consultasInserccion = "INSERT INTO "+ tabla + " VALUES("+ valores + ");";
			System.out.println(consultasInserccion);
			//Creación del Statement para poder realizar la consulta
			Statement consul = conexion.createStatement();
			//Ejecución de la consulta
			consul.executeUpdate(consultasInserccion);
			System.out.println("Datos insertados correctamente");
			//Cierre del Statement
			consul.close();
		}finally {
			//Cierre de la conexión
			cerrarConexion(conexion);
		}
	}



	public void deleteData(String tabla, String idEscrito) throws SQLException{
		Connection conexion = conectar();
		try {

			//Datos a insertar
			String consultasBorrar = "DELETE FROM " + tabla + " where id = " + idEscrito;
			System.out.println(consultasBorrar);
			//Creación del Statement para poder reqalizar la consulta
			Statement consul = conexion.createStatement();
			//Ejecución de la consulta
			consul.executeUpdate(consultasBorrar);
			System.out.println("Datos borrados correctamente");
			//Cierre del Statement
			consul.close();
		}finally {
			//Cierre de la conexión
			cerrarConexion(conexion);
		}
	}


	public String getONEData(String consulta) throws SQLException {
		Connection conexion = conectar();
		String dato = "";

		if (conexion != null) {
			try {
				Statement statement = conexion.createStatement();
				ResultSet resultSet = statement.executeQuery(consulta);
				ResultSetMetaData metaData = resultSet.getMetaData();

				//Ponemos un if porque solo va a devolver un dato 
				if (resultSet.next()) {
					dato = resultSet.getString(1); //Para obtener el primer dato de la primera columna
				}

				System.out.println("Dato recuperado correctamente");
				statement.close();
			} finally {
				cerrarConexion(conexion);
			}
		}
		return dato;
	}

	public String[][] getData(String consulta) throws SQLException {
	    Connection conexion = conectar();
	    String[][] datos = null; //Inicializamos la matriz bidimensional de cadenas como nula

	    // Verificamos si la conexión no es nula para que no se parta el programa
	    if (conexion != null) {
	        try {
	            //Creamos un objeto Statement para hacer la consulta SQL
	            Statement statement = conexion.createStatement();
	            //Hacemos la consulta y obtenemos los resultados en un objeto ResultSet
	            ResultSet resultSet = statement.executeQuery(consulta);
	            //obtenemos la metadata de los resultados para coger el número de columnas
	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            //Creamos un ArrayList para almacenar los datos
	            ArrayList<String[]> dataList = new ArrayList<>();

	            //Iteramos sobre los resultados del ResultSet
	            while (resultSet.next()) {
	                //Creamos una cadena para almacenar los datos de la fila
	                String[] fila = new String[columnCount];
	                //Iteramos sobre cada columna y guardamos su valor en la cadena
	                for (int i = 1; i <= columnCount; i++) {
	                    fila[i - 1] = resultSet.getString(i);
	                }
	                //Añadimos la fila al ArrayList
	                dataList.add(fila);
	            }

	            //Convertimos el ArrayList a una matriz bidimensional
	            datos = new String[dataList.size()][columnCount];
	            for (int i = 0; i < dataList.size(); i++) {
	                datos[i] = dataList.get(i);
	            }

	            System.out.println("Datos recuperados correctamente");
	            
	            statement.close();
	        } finally {
	            cerrarConexion(conexion);
	        }
	    }
	    
	    return datos;
	}


	public String[][] getAllData(String tabla) throws SQLException {
		Connection conexion = conectar();
		String[][] datos = null;

		if (conexion != null) {
			try {
				String consulta = "SELECT * FROM " + tabla;
				Statement statement = conexion.createStatement();
				ResultSet resultSet = statement.executeQuery(consulta);
				ResultSetMetaData metaData = resultSet.getMetaData();
				int columnCount = metaData.getColumnCount();

				ArrayList<String[]> dataList = new ArrayList<>();

				while (resultSet.next()) {
					String[] fila = new String[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						fila[i - 1] = resultSet.getString(i);
					}
					dataList.add(fila);
				}

				datos = new String[dataList.size()][columnCount];
				for (int i = 0; i < dataList.size(); i++) {
					datos[i] = dataList.get(i);
				}

				System.out.println("Datos recuperados correctamente");
				statement.close();
			} finally {
				cerrarConexion(conexion);
			}
		}

		return datos;
	}
	
	
	public int obtenerUltimoIdVenta() {
		Conexion conexion = new Conexion();
		Connection cn = null;

		try {
			cn = conexion.conectar();
			String ultimaId = conexion.getONEData("SELECT MAX(id) FROM venta");

			if (ultimaId == null) {
				return 0; //Si no hay ninguna id registrada devolvemos 0
			} else {
				return Integer.parseInt(ultimaId) + 1; //Si la última id existe parseamos la id a int y sumamos 1 para que no se sobreescriban las id
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; //Si falla la conexión devolvemos 0
		}
	}
}