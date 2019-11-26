package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Modelo.Camion;
import Modelo.Extra;
import Modelo.Turismo;

public class TurismoDAO {

	public TurismoDAO() {

	}

	public boolean annadirTurismos(Turismo turismoAnnadir) {
		HashMap<String, Turismo> listaTurismos = leerTodosTurismos();
		if (listaTurismos.containsKey(turismoAnnadir.getMatricula())) {
			System.out.println("Turismo repetido");
			return false;
		} else {
			System.out.println("Turismo insertado");
			listaTurismos.put(turismoAnnadir.getMatricula(), turismoAnnadir);
			imprimirTodosTurismos(listaTurismos);
			return true;
		}
	}

	public Turismo buscarTurismoLista(String matriculaTurismoBuscar) {
		HashMap<String, Turismo> listaTurismos = leerTodosTurismos();
		Turismo turismoEncontrado;
		if (listaTurismos.containsKey(matriculaTurismoBuscar)) {
			turismoEncontrado = listaTurismos.get(matriculaTurismoBuscar);
		} else {
			turismoEncontrado = new Turismo();
			System.out.println("Camion no encontrado");
		}
		return turismoEncontrado;
	}
	
	public void annadirTurismosModificados(Turismo turismoAnnadir) {
		HashMap<String, Turismo> listaTurismos = leerTodosTurismos();

		listaTurismos.put(turismoAnnadir.getMatricula(), turismoAnnadir);
		imprimirTodosTurismos(listaTurismos);
		System.out.println("Turismo insertado");

	}

	public boolean modificarTurismos(Turismo turismoEditar, String matriculaOriginal) {
		HashMap<String, Turismo> listaTurismos = leerTodosTurismos();

		if (listaTurismos.containsKey(matriculaOriginal)) {
			listaTurismos.remove(matriculaOriginal);
			annadirTurismosModificados(turismoEditar);
			return true;
		} else {
			System.out.println("El turismo no existe");
			return false;
		}
	}

	public boolean eliminarTurismos(Turismo turismoEliminar) {
		HashMap<String, Turismo> listaTurismos = leerTodosTurismos();
		if (listaTurismos.containsKey(turismoEliminar.getMatricula())) {
			listaTurismos.remove(turismoEliminar.getMatricula());
			imprimirTodosTurismos(listaTurismos);
			return true;
		} else {
			System.out.println("El turismo no existe");
			return false;
		}

	}

	public void imprimirTodosTurismos(HashMap<String, Turismo> listaTodosTurismos) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("turismos.txt"));

			for (Map.Entry<String, Turismo> registro : listaTodosTurismos.entrySet()) {
				out.println("Matricula:");
				out.println(registro.getValue().getMatricula());
				out.println("Marca:");
				out.println(registro.getValue().getMarca());
				out.println("Modelo:");
				out.println(registro.getValue().getModelo());
				out.println("Color:");
				out.println(registro.getValue().getColor());
				out.println("Puertas:");
				out.println(registro.getValue().getNumPuertas());
				out.println("Precio:");
				String precio = registro.getValue().getPrecio()+"";
				precio = precio.replace(".", ",");
				out.println(precio);
				out.println("Extras");
				out.println(registro.getValue().getExtras().getIdentificador());
			}
			out.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Fichero turismos.txt no encontrado");
		} catch (IOException e2) {
			System.err.println("Error IO");
		}
	}

	public HashMap<String, Turismo> leerTodosTurismos() {
		HashMap<String, Turismo> listaTurismos = new HashMap<String, Turismo>();
		try {
			HashMap<Integer, Extra> listaExtras = new HashMap<Integer, Extra>();
			
			Connection con =null;
			Statement stm = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM Turismos";
			
			con = DBConnect.conectarBD();
			stm = con.createStatement();
			
			rs = stm.executeQuery(sql);
			
			ExtraDAO extradao = new ExtraDAO();
			listaExtras = extradao.leerTodosExtras();

			while(rs.next()){
				Struct vehiStruct = (Struct) rs.getObject(2);
				Object [] vehiAttr = vehiStruct.getAttributes();
				
				String matricula= vehiAttr[0].toString();
				String marca= vehiAttr[1].toString();
				String modelo= vehiAttr[2].toString();
				String color= vehiAttr[3].toString();
				double precio = Double.parseDouble(vehiAttr[4].toString());
				
				
			}
			
				int extra = in.nextInt();
				Extra extraTurismo = null;
				if (listaExtras.containsKey(extra)) {
					extraTurismo = listaExtras.get(extra);
				} else {
					extraTurismo = listaExtras.get(0);
				}
				Turismo turismoC = new Turismo(Matricula, Marca, Modelo, Color, Precio, numPuertas, extraTurismo);
				listaTurismos.put(Matricula, turismoC);
			} while (in.hasNext());
			in.close();
		} catch (SQLException e) {
			System.err.println("Fichero no encontrado");
		}

		return listaTurismos;
	}
}
