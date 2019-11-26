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

import Modelo.Extra;

public class ExtraDAO {

	public HashMap<Integer, Extra> leerTodosExtras() {
		HashMap<Integer, Extra> listaExtras = new HashMap<Integer, Extra>();
		try {
			Connection con = null;
			Statement stm = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM Extras";
			
			con= DBConnect.conectarBD();
			stm = con.createStatement();
			rs=stm.executeQuery(sql);
			
			while(rs.next()){
				Struct extraStruct = ((Struct)rs.getObject(1));
				Object [] extraAttr = extraStruct.getAttributes();
				
				int id =Integer.parseInt(extraAttr[0].toString());
				String des = extraAttr[1].toString();
				
				Extra miExtra = new Extra(id, des);
				
				listaExtras.put(miExtra.getIdentificador(), miExtra);
			}
		stm.close();
		con.close();
		}catch (SQLException e) {
			System.err.println("Error ExtraDAO SQL");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return listaExtras;
	}

	public void imprimirTodosExtras(HashMap<Integer, Extra> listaExtras) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter("extras.txt"));

			for (Map.Entry<Integer, Extra> registro : listaExtras.entrySet()) {
				out.println("Identificador:");
				out.println(registro.getValue().getIdentificador());
				out.println("Descripcion:");
				out.println(registro.getValue().getDescripcion());
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fichero extras no encontrado");
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public boolean annadirExtra(Extra extraAnnadir) {
		HashMap<Integer, Extra> listaExtras = leerTodosExtras();
		if (listaExtras.containsKey(extraAnnadir.getIdentificador())) {
			System.err.println("Idenficador repetido");
			return false;
		} else {
			listaExtras.put(extraAnnadir.getIdentificador(), extraAnnadir);
			imprimirTodosExtras(listaExtras);
			System.out.println("Extra annadido correctamente");
			return true;
		}

	}

	public Extra buscarExtra(int Identificador) {
		HashMap<Integer, Extra> listaExtras = leerTodosExtras();
		Extra extraEncontrado;
		if (listaExtras.containsKey(Identificador)) {
			return extraEncontrado = listaExtras.get(Identificador);
		} else {
			return extraEncontrado = listaExtras.get(0);
		}
	}

	public boolean eliminarExtra(Extra extraEliminar) {
		HashMap<Integer, Extra> listaExtras = leerTodosExtras();
		if (listaExtras.containsKey(extraEliminar.getIdentificador())) {
			listaExtras.remove(extraEliminar.getIdentificador());
			imprimirTodosExtras(listaExtras);
			System.out.println("Extra eliminado correctamente");
			return true;
		} else {
			System.err.println("El extra introducido no existe");
			return false;
		}
	}

}
