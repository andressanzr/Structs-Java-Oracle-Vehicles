package Modelo;

import java.util.HashMap;


import Controlador.TurismoDAO;

public class Turismo extends Vehiculo {
	private int NumPuertas;
	private Extra Extras;

	public Turismo(String matricula, String marca, String modelo, String color, double precio, int numPuertas,
			Extra extras) {
		super(matricula, marca, modelo, color, precio);
		NumPuertas = numPuertas;
		Extras = extras;
	}

	public HashMap<String, Turismo> leerTodosTurismos() {
		TurismoDAO turismoDAO = new TurismoDAO();
		return turismoDAO.leerTodosTurismos();
	}

	public boolean annadirTurismo() {
		TurismoDAO turismoDAO = new TurismoDAO();

		return turismoDAO.annadirTurismos(this);
	}
	public Turismo buscarTurismo(String matriculaTurismoBuscar) {
		TurismoDAO turismoDAO = new TurismoDAO();

		return turismoDAO.buscarTurismoLista(matriculaTurismoBuscar);
	}
	public boolean modificarTurismo(String matriculaOriginal) {
		TurismoDAO turismoDAO = new TurismoDAO();
		return turismoDAO.modificarTurismos(this, matriculaOriginal);
	}

	public boolean eliminarTurismo() {
		TurismoDAO turismoDAO = new TurismoDAO();
		return turismoDAO.eliminarTurismos(this);
	}

	public Turismo() {

	}


	public int getNumPuertas() {
		return NumPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		NumPuertas = numPuertas;
	}

	public Extra getExtras() {
		return Extras;
	}

	public void setExtras(Extra extras) {
		Extras = extras;
	}

	@Override
	public String toString() {
		return "Turismo [NumPuertas=" + NumPuertas + ", Extras=" + Extras + " " + super.toString() + "]";
	}


}
