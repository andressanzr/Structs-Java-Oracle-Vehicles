package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static Connection conectarBD () {
		Connection conexion = null;
		
		String user = "system";
		String pass = "admin";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			conexion = DriverManager.getConnection(url, user, pass);
			
		} catch (Exception e) {

		}
		return conexion;
	}
}
