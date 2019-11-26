package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		HashMap<String, Camion> listaCamiones = new HashMap<String, Camion>();
		
		try{
			con = DBConnect.conectarBD();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()){

					System.out.println(1);
			}
			
			rs.close();
			stm.close();
			con.close();
		}catch(SQLException e){
			System.err.println("Error SQL CamionDAO ");
			e.printStackTrace();
		}
		/*
		try {
			Scanner in = new Scanner(new FileReader("empleados.txt"));
			do {
				in.next();
				String Usuario = in.next();
				in.next();
				String Password = in.next();
				Empleado empleado1= new Empleado(Usuario, Password);

				listaEmpleados.put(Usuario, empleado1);
			} while (in.hasNext());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado");
		}*/
		return listaEmpleados;
	}
}
