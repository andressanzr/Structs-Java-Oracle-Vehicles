package Modelo;

import java.util.HashMap;

import Controlador.ExtraDAO;

public class Extra {
	private int Identificador;
	private String Descripcion;
	
	public Extra(){
		
	}
	public Extra(int identificador, String descripcion) {
		Identificador = identificador;
		Descripcion = descripcion;
	}

	public HashMap<Integer, Extra> leerTodosExtras() {
		ExtraDAO extraDao = new ExtraDAO();
		return extraDao.leerTodosExtras();
	}

	public void imprimirTodosExtras(HashMap<Integer, Extra> listaExtras) {
		ExtraDAO extraDao = new ExtraDAO();
		extraDao.imprimirTodosExtras(listaExtras);
	}

	public boolean annadirExtra() {
		ExtraDAO extraDao = new ExtraDAO();
		return extraDao.annadirExtra(this);
	}

	public Extra buscarExtra(int Identificador) {
		ExtraDAO extraDao = new ExtraDAO();
		return extraDao.buscarExtra(Identificador);
	}
	public boolean eliminarExtra() {
		ExtraDAO extraDao = new ExtraDAO();
		return extraDao.eliminarExtra(this);
	}

	public int getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(int identificador) {
		Identificador = identificador;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Extra [Identificador=" + Identificador + ", Descripcion=" + Descripcion + "]\n";
	}

}
