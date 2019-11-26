package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Scanner;

import Modelo.Camion;
import Modelo.Empleado;

public class EmpleadoDAO {

	public EmpleadoDAO(){

	}
	public HashMap<String, Empleado> leerTodosEmpleados() {
		HashMap<String, Empleado> listaEmpleados = new HashMap<String, Empleado>();
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Empleados";
		
		try{
			con = DBConnect.conectarBD();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()){
				
				Struct empleadoStruct = (Struct) rs.getObject(1);
				String nombreUsuario = empleadoStruct.getAttributes()[0].toString();	
				String passUsuario = empleadoStruct.getAttributes()[1].toString();
				
				Empleado empleadoAdd = new Empleado(nombreUsuario, passUsuario);
				
				listaEmpleados.put(nombreUsuario, empleadoAdd);
			}
			
			stm.close();
			con.close();
		}catch(SQLException e){
			System.err.println("Error SQL EmpleadoDAO ");
			e.printStackTrace();
		}
		
		return listaEmpleados;
	}
}
