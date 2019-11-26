package Modelo;

public abstract class Vehiculo {
	private String Matricula;
	private String Marca;
	private String Modelo;
	private String Color;
	private double Precio;


	public Vehiculo(String matricula, String marca, String modelo, String color, double precio) {
		Matricula = matricula;
		Marca = marca;
		Modelo = modelo;
		Color = color;
		Precio = precio;
	}

	public Vehiculo() {

	}

	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}

	@Override
	public String toString() {
		return "Vehiculo [Matricula=" + Matricula + ", Marca=" + Marca + ", Modelo=" + Modelo + ", Color=" + Color
				+ ", Precio=" + Precio + "]\n";
	}



}
