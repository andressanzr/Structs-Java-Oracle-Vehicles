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
			System.out.println("Camion insertado");
			listaCamiones.put(camionAnnadir.getMatricula(), camionAnnadir);
			imprimirTodosCamiones(listaCamiones);
			return true;
		}

	}

	public void annadirCamionesModificados(Camion camionAnnadir) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		listaCamiones.put(camionAnnadir.getMatricula(), camionAnnadir);
		imprimirTodosCamiones(listaCamiones);
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
			imprimirTodosCamiones(listaCamiones);
			System.out.println("Camion eliminado");
			return true;
		} else {
			System.out.println("El camion no existe");
			return false;
		}

	}

	public Camion buscarCamionLista(String matriculaCamionBuscar) {
		HashMap<String, Camion> listaCamiones = leerTodosCamiones();
		Camion camionEncontrado;
		if (listaCamiones.containsKey(matriculaCamionBuscar)) {
			camionEncontrado = listaCamiones.get(matriculaCamionBuscar);
		} else {
			camionEncontrado = new Camion();
			System.out.println("Camion no encontrado");
		}
		return camionEncontrado;
	}

	public HashMap<String, Camion> leerTodosCamiones() {
		Connection con = null;
		OracleStatement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camiones";

		HashMap<String, Camion> listaCamiones = new HashMap<String, Camion>();

		try {
			con = DBConnect.conectarBD();
			stm = (OracleStatement) con.createStatement();
			rs = (OracleResultSet) stm.executeQuery(sql);

			while (rs.next()) {
				Struct camionStruct = (Struct) rs.getObject(2);

				Object[] camionAttr = camionStruct.getAttributes();

				Object[] vehiAttr = ((Struct) camionAttr[0]).getAttributes();
				
				BigDecimal b= (BigDecimal) camionAttr[1];
				int capacidadCarga = b.intValue();
				Camion camAnnadir = new Camion(vehiAttr[0].toString(), vehiAttr[1].toString(), vehiAttr[2].toString(), vehiAttr[3].toString(),Double.parseDouble(vehiAttr[4].toString()), capacidadCarga);
				
				listaCamiones.put(camAnnadir.getMatricula(), camAnnadir);
			}

			rs.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error SQL CamionDAO ");
			e.printStackTrace();
		}

		/*
		 * try { Scanner in = new Scanner(new FileReader("camiones.txt")); do {
		 * in.next(); String Matricula = in.next(); in.next(); String Marca =
		 * in.next(); in.next(); in.nextLine(); String Modelo = in.nextLine();
		 * in.next(); String Color = in.next(); in.next(); double Precio =
		 * in.nextDouble(); in.next(); int CapacidadCarga = in.nextInt(); Camion
		 * miCamion = new Camion(Matricula, Marca, Modelo, Color, Precio,
		 * CapacidadCarga); listaCamiones.put(Matricula, miCamion); } while
		 * (in.hasNext()); in.close(); } catch (FileNotFoundException e) {
		 * System.err.println("Fichero no encontrado"); }
		 */
		return listaCamiones;
	}

	public void imprimirTodosCamiones(HashMap<String, Camion> listaTodosCamiones) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("camiones.txt"));

			for (Map.Entry<String, Camion> registro : listaTodosCamiones.entrySet()) {
				out.println("Matricula");
				out.println(registro.getValue().getMatricula());
				out.println("Marca");
				out.println(registro.getValue().getMarca());
				out.println("Modelo");
				out.println(registro.getValue().getModelo());
				out.println("Color");
				out.println(registro.getValue().getColor());
				out.println("Precio");
				String precio = registro.getValue().getPrecio() + "";
				precio = precio.replace(".", ",");
				out.println(precio);
				out.println("CapacidadCarga");
				out.println(registro.getValue().getCapacidadCarga());
			}

			out.close();

		} catch (FileNotFoundException e1) {
			System.err.println("Fichero camiones.txt no encontrado");
		} catch (IOException e2) {
			System.err.println("Error IO");
		}
	}
}
