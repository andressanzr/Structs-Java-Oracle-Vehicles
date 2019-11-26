package Modelo;

import java.util.HashMap;

import Controlador.EmpleadoDAO;

public class Empleado {
	private String Usuario;
	private String Password;

	public Empleado(String usuario, String password) {
		Usuario = usuario;
		Password = password;
	}
	public Empleado(){
		
	}
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public boolean comprobarLogin(String usuario, String password) {
		EmpleadoDAO empleadoLeer = new EmpleadoDAO();
		HashMap<String, Empleado> listaTodosEmpleados = empleadoLeer.leerTodosEmpleados();
		if (listaTodosEmpleados.containsKey(usuario)) {
			Empleado empleadoLogin = listaTodosEmpleados.get(usuario);
			
			boolean usuarioOK= empleadoLogin.getUsuario().equals(usuario);
			
			boolean contrasenaOK=empleadoLogin.getPassword().equals(password);
			
			if(usuarioOK && contrasenaOK){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

}
