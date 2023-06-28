package dam2.add.p22.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dam2.add.p22.UsuarioDAO;
import dam2.add.p22.jdbc.Conexion;
import dam2.add.p22.modelo.Municipio;
import dam2.add.p22.modelo.Provincia;
import dam2.add.p22.modelo.Usuario;

import java.sql.PreparedStatement;

/**
 * @author davidm
 */
public class OperacionesBDTotal {

	private static Connection conexion;
	private static Statement st;

	// CREAR BASE DE DATOS
	public static void crearBaseDeDatos() throws SQLException {
		
		conexion = ConexionTest.getConexion();
		
		st = conexion.createStatement();
		
		st.executeUpdate("CREATE DATABASE IF NOT EXISTS Main");   //solo si no existe
	}

	// CREAR TABLA
	public static void crearTabla() throws SQLException {
		
		conexion = Conexion.getConexion();
		
		st = conexion.createStatement();
		
		st.executeQuery("use Main"); 

		st.executeUpdate("CREATE TABLE IF NOT EXISTS Usuario ("			//solo si no existe
		+ "id INT AUTO_INCREMENT, " 
		+ "nombre VARCHAR(255), "
		+ "password VARCHAR(255), " 
		+ "provincia VARCHAR(255), " 
		+ "municipio VARCHAR(255), " 
		+ "bloqueado boolean, " 
		+ "PRIMARY KEY(id)" 
		+ ")");

	}
	
	// BORRAR TABLA
		public static void borrarTabla() throws SQLException {
			
			conexion = ConexionTest.getConexion();
			
			st = conexion.createStatement();
			
			st.executeQuery("use Main"); 

			st.executeUpdate("DROP TABLE IF EXISTS Usuario");

		}
		
   //CONSULTAR VACIA
		
		public static boolean consultarVacia() throws SQLException {

			boolean vacia = false;
			int num = 0;
			int total = 0;

			num = UsuarioDAO.consulta4("SELECT * FROM usuario");
			if (num==0) vacia = true;
			return vacia;
		}

	

}
