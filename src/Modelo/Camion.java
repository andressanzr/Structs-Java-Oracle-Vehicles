package Modelo;

import java.util.HashMap;

import Controlador.CamionDAO;
import Controlador.TurismoDAO;

public class Camion extends Vehiculo {
	private int CapacidadCarga;

	public Camion() {

	}

	public Camion(String matricula, String marca, String modelo, String color, double precio, int capacidadCarga) {
		super(matricula, marca, modelo, color, precio);
		CapacidadCarga = capacidadCarga;
	}

	public HashMap<String, Camion> leerTodosCamiones() {
		CamionDAO camionDao = new CamionDAO();
		return camionDao.leerTodosCamiones();
	}

	public boolean annadirCamion() {
		CamionDAO camionDao = new CamionDAO();
		return camionDao.annadirCamiones(this);
	}

	public boolean modificarCamion(String matriculaOriginal) {
		CamionDAO camionDao = new CamionDAO();
		return camionDao.modificarCamiones(this, matriculaOriginal);
	}

	public Camion buscarCamion(String matriculaCamionBuscar) {
		CamionDAO camionDao = new CamionDAO();

		return camionDao.buscarCamionLista(matriculaCamionBuscar);
	}
	public boolean eliminarCamion() {
		CamionDAO camionDao = new CamionDAO();
		return camionDao.eliminarCamiones(this);
	}

	public int getCapacidadCarga() {
		return CapacidadCarga;
	}

	public void setCapacidadCarga(int capacidadCarga) {
		CapacidadCarga = capacidadCarga;
	}

	@Override
	public String toString() {
		return "Camion [CapacidadCarga=" + CapacidadCarga + "] " + super.toString() + "]";
	}

}
