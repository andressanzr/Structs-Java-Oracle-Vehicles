package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Modelo.Camion;
import Modelo.Vehiculo;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import Controlador.DBConnect;

public class CamionDAO {

	public CamionDAO() {

	}

	public boolean annadirCamiones(Camion camionAnnadir) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		if (listaCamiones.containsKey(camionAnnadir.getMatricula())) {
			System.out.println("Camion repetido");
			return false;
		} else {
			
			
			String sql = "INSERT INTO Camiones VALUES (new Camion(new Vehiculo('"+camionAnnadir.getMatricula()+"', '"+camionAnnadir.getMarca()+"','"+camionAnnadir.getModelo()+"', '"+ camionAnnadir.getColor()+"', "+camionAnnadir.getPrecio() +"), "+camionAnnadir.getCapacidadCarga()+"))"; 
			
			try {
				Connection con = DBConnect.conectarBD();
				Statement stm = con.createStatement();
				stm.execute(sql);
				
				boolean inserted = true;
				if(inserted) {
					System.out.println("Camion insertado");
				}
			} catch (SQLException e) {
				System.err.println("Error CamionDAO annadirCamiones");
				e.printStackTrace();
			}
			
			listaCamiones.put(camionAnnadir.getMatricula(), camionAnnadir);
			
			return true;
		}

	}

	public void annadirCamionesModificados(Camion camionAnnadir) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		listaCamiones.put(camionAnnadir.getMatricula(), camionAnnadir);
		System.out.println("Camion insertado");
	}

	public boolean modificarCamiones(Camion camionEditar, String matriculaOriginal) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();

		if (listaCamiones.containsKey(matriculaOriginal)) {

			listaCamiones.remove(matriculaOriginal);
			annadirCamionesModificados(camionEditar);
			return true;
		} else {
			System.out.println("El camion no existe");
			return false;
		}

	}

	public boolean eliminarCamiones(Camion camionEliminar) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		if (listaCamiones.containsKey(camionEliminar.getMatricula())) {
			listaCamiones.remove(camionEliminar.getMatricula());
			
			Connection con =null;
			Statement stm = null;
			ResultSet rs = null;
			String sql = "DELETE FROM Camiones c where c.cam.vehi.matricula='"+camionEliminar.getMatricula()+"'";
			
			try {
				con = DBConnect.conectarBD();
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
				}
			} catch (SQLException e) {
				System.err.println("Error CamionDAO eliminarCamiones");
				e.printStackTrace();
			}
			
			
			System.out.println("Camion eliminado");
			return true;
		} else {
			System.out.println("El camion no existe");
			return false;
		}
	}

	public Camion buscarCamionLista(String matriculaCamionBuscar) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		Camion camionEncontrado = null;
		Connection con =null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camiones c where c.cam.vehi.matricula='"+matriculaCamionBuscar+"'";
		
		try {
			con = DBConnect.conectarBD();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Struct camionStruct = (Struct) rs.getObject(1);
				
				Object[] camionAttr = camionStruct.getAttributes();
				Object[] vehiAttr = ((Struct)camionAttr[1]).getAttributes();
				
				BigDecimal a = (BigDecimal) camionAttr[2];
				int capacidadCarga = a.intValue();
				
				camionEncontrado = new Camion(vehiAttr[0].toString(), vehiAttr[1].toString(), vehiAttr[2].toString(), vehiAttr[3].toString(),Double.parseDouble(vehiAttr[4].toString()), capacidadCarga);
			}
		} catch (SQLException e) {
			System.err.println("Error CamionDAO buscarCamionLista");
			e.printStackTrace();
		}
		if(camionEncontrado==null) {
			camionEncontrado = new Camion();
			System.out.println("Camion no encontrado");
		}
		
		return camionEncontrado;
	}

	public HashMap<String, Camion> leerTodosCamiones() {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camiones";

		HashMap<String, Camion> listaCamiones = new HashMap<String, Camion>();

		try {
			con = DBConnect.conectarBD();
			stm = con.createStatement();
			rs =  stm.executeQuery(sql);

			while (rs.next()) {
				Struct camionStruct = (Struct) rs.getObject(1);

				Object[] camionAttr = camionStruct.getAttributes();

				Object[] vehiAttr = ((Struct) camionAttr[0]).getAttributes();
				
				BigDecimal b= (BigDecimal) camionAttr[1];
				int capacidadCarga = b.intValue();
				Camion camAnnadir = new Camion(vehiAttr[0].toString(), vehiAttr[1].toString(), vehiAttr[2].toString(), vehiAttr[3].toString(),Double.parseDouble(vehiAttr[4].toString()), capacidadCarga);
				
				listaCamiones.put(camAnnadir.getMatricula(), camAnnadir);
			}

			stm.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error SQL CamionDAO ");
			e.printStackTrace();
		}

		return listaCamiones;
	}

	
}
