package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import Modelo.Camion;
import Modelo.Empleado;
import Modelo.Extra;
import Modelo.Turismo;



class Principal {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, IOException {
			
			// declaracion variables
			String contrasena, usuario;
			boolean loginCorrecto = false;
			boolean cerrarSesion = false;

			// declaracion listas HashMap
			HashMap<String, Turismo> listaTurismos = new HashMap<String, Turismo>();
			HashMap<String, Camion> listaCamiones = new HashMap<String, Camion>();
			HashMap<Integer, Extra> listaExtras = new HashMap<Integer, Extra>();

			// inicializacion objetos para lectura de datos
			Camion camionLeer = new Camion();
			Turismo turismoLeer = new Turismo();
			Extra extraLeer = new Extra();

			// mensaje de bienvenida
			Scanner sc = new Scanner(System.in);
			System.out.println("Bienvenido al programa de gestion de vehiculos");
			do {
				// bucle login usuario
				do {
					
					System.out.println("Introduzca su usuario:");
					usuario = in.next();
					System.out.println("Introduzca su contrasenna:");
					contrasena = in.next();
					Empleado empleadoComprobarLogin = new Empleado();
					loginCorrecto = empleadoComprobarLogin.comprobarLogin(usuario, contrasena);
					if (loginCorrecto) {
						System.out.println("Usuario y contrasenna correctos");

					} else {
						System.err.println("Usuario o contrasenna erroneos");
					}
				} while (loginCorrecto == false);
				int opcion = 0;
				try {
					do {
						cerrarSesion=false;
						// menu principal
						System.out.println("Elija que opcion desea realizar.\r\n" + "1.	Mostrar todos los vehiculos.\r\n"
								+ "2.	Buscar un vehiculo.\r\n" + "3.	Annadir un vehiculo.\r\n"
								+ "4.	Modificar un vehiculo.\r\n" + "5.	Eliminar un vehiculo.\r\n"
								+ "6.	Mostrar los extras disponibles.\r\n" + "7.	Annadir un extra.\r\n"
								+ "8.	Eliminar un extra.\r\n" + "9.	Log out." + "");
						try {
							opcion = sc.nextInt();
						} catch (InputMismatchException e1) {
							System.err.println("No se pueden introducir letras");
							sc.nextLine();
						}

						// switch opcion elegida del menu principal
						switch (opcion) {
						case 1:
							// Mostrar todos los vehiculos
							listaCamiones = camionLeer.leerTodosCamiones();
							System.out.println(listaCamiones.toString());

							listaTurismos = turismoLeer.leerTodosTurismos();
							System.out.println(listaTurismos.toString());
							break;
						case 2:
							// Buscar un vehiculo
							listaCamiones = camionLeer.leerTodosCamiones();
							listaTurismos = turismoLeer.leerTodosTurismos();
							boolean buscarMatriculaOK = false;
							// bucle matricula correcta
							do {
								System.out.println("Introduce la matricula del vehiculo que quieres buscar");
								String Matricula = sc.next();
								if (listaCamiones.containsKey(Matricula)) {
									System.out.println(listaCamiones.get(Matricula));
									buscarMatriculaOK = true;
								} else if (listaTurismos.containsKey(Matricula)) {
									System.out.println(listaTurismos.get(Matricula));
									buscarMatriculaOK = true;
								} else {
									System.err.println("El vehiculo no existe");
									buscarMatriculaOK = false;
								}
							} while (!buscarMatriculaOK);
							break;
						case 3:
							// annadir vehiculo
							System.out.println("0. Camion\n1. Turismo");
							int elegir = 9;
							try {
								elegir = sc.nextInt();
							} catch (Exception e1) {
								System.err.println("No se pueden introducir letras");
								sc.nextLine();
							}
							// caso annadir camion
							if (elegir == 0) {
								try {
									listaCamiones = camionLeer.leerTodosCamiones();
									System.out.println("Ingresa la nueva matricula");
									String matricula = sc.next();
									System.out.println("Ingresa la marca");
									String marca = sc.next();
									System.out.println("Ingresa el modelo");
									String modelo = sc.next();
									System.out.println("Ingresa el color");
									String color = sc.next();
									System.out.println("Ingresa el precio");
									double precio = sc.nextDouble();
									System.out.println("Ingresa la capacidad de carga");
									int capacidadCarga = sc.nextInt();
									Camion camionAnnadir = new Camion(matricula, marca, modelo, color, precio,
											capacidadCarga);
									camionAnnadir.annadirCamion();
								} catch (InputMismatchException e) {
									System.err.println("No se pueden introducir letras en la capacidad de carga/precio ");
								}

								// caso annadir turismo
							} else if (elegir == 1) {
								try {
									listaTurismos = turismoLeer.leerTodosTurismos();
									listaExtras = extraLeer.leerTodosExtras();
									Extra extraTurismo;
									System.out.println("Ingresa la nueva matricula");
									String matricula = sc.next();
									System.out.println("Ingresa la marca");
									String marca = sc.next();
									System.out.println("Ingresa el modelo");
									String modelo = sc.next();
									System.out.println("Ingresa el color");
									String color = sc.next();
									System.out.println("Ingresa el precio");
									double precio = sc.nextDouble();
									System.out.println("Ingresa el numero de puertas");
									int numPuertas = sc.nextInt();
									System.out.println("Ingresa identificador del extra");
									int extra = sc.nextInt();
									extraTurismo = extraLeer.buscarExtra(extra);

									Turismo turismoAnnadir = new Turismo(matricula, marca, modelo, color, precio,
											numPuertas, extraTurismo);
									turismoAnnadir.annadirTurismo();
								} catch (InputMismatchException e) {
									System.err.println("No se pueden introducir letras en el numero de puertas/precio ");
								}
							}

							break;

						case 4:
							// Modificar vehiculo
							System.out.println("Introduce la matricula del vehiculo que quieres modificar");
							String matriculaOriginal = sc.next();
							boolean matriculaOK = false;
							do {
								// comprobar que el vehiculo existe
								if (listaCamiones.containsKey(matriculaOriginal)
										|| listaTurismos.containsKey(matriculaOriginal)) {
									int tipo = 0;
									matriculaOK = true;

									Camion camionModificar = null;
									Turismo turismoModificar = null;
									// tipo=1 camion, tipo=2 trusimo
									try {
										System.out.println("que quieres modificar");
										System.out.println("1.Matricula");
										System.out.println("2.Marca");
										System.out.println("3.Modelo");
										System.out.println("4.Color");
										System.out.println("5.Precio");

										// imprimir en caso que sea camion
										if (listaCamiones.containsKey(matriculaOriginal)) {
											System.out.println("6.Capacidad Carga");
											tipo = 1;
											camionModificar = listaCamiones.get(matriculaOriginal);
										}
										// imprimir en caso que sea turismo
										if (listaTurismos.containsKey(matriculaOriginal)) {
											System.out.println("6.Numero de puertas");
											System.out.println("7.Identificador del extra");
											tipo = 2;
											turismoModificar = listaTurismos.get(matriculaOriginal);
										}
										// propiedad a cambiar del vehiculo
										int seleccion = 0;
										try {
											seleccion = sc.nextInt();
										} catch (InputMismatchException e) {
											System.err.println("No se pueden introducir letras");
										}

										// inicializacion variables clase vehiculo
										double precio = 0;
										String color = null;
										String matricula = null;
										String marca = null;
										String modelo = null;

										// inicializacion variables clase camion
										int capacidadCarga = 0;

										// inicializacion variables clase turismo
										int numPuertas = 0;
										int extra;
										Extra extraTurismo = null;

										// guardar datos camion
										if (tipo == 1) {
											matricula = matriculaOriginal;
											marca = camionModificar.getMarca();
											modelo = camionModificar.getModelo();
											color = camionModificar.getColor();
											precio = camionModificar.getPrecio();
											capacidadCarga = camionModificar.getCapacidadCarga();
										}
										// guardar datos turismo
										if (tipo == 2) {
											matricula = matriculaOriginal;
											marca = turismoModificar.getMarca();
											modelo = turismoModificar.getModelo();
											color = turismoModificar.getColor();
											precio = turismoModificar.getPrecio();
											numPuertas = turismoModificar.getNumPuertas();
											extraTurismo = turismoModificar.getExtras();
										}
										// propiedad a cambiar
										switch (seleccion) {
										case 1:
											System.out.println("Ingresa la nueva matricula");
											matricula = sc.next();
											break;
										case 2:
											System.out.println("Ingresa la marca");
											marca = sc.next();
											break;
										case 3:
											System.out.println("Ingresa el modelo");
											modelo = sc.next();
											break;
										case 4:
											System.out.println("Ingresa el color");
											color = sc.next();
											break;
										case 5:
											System.out.println("Ingresa el precio");
											precio = sc.nextDouble();
											break;

										case 6:
											// caso sea camion
											if (listaCamiones.containsKey(matriculaOriginal)) {
												System.out.println("Ingresa la capacidad de carga");
												capacidadCarga = sc.nextInt();
											}
											// caso sea turismo
											if (listaTurismos.containsKey(matriculaOriginal)) {
												System.out.println("Ingresa el numero de puertas");
												numPuertas = sc.nextInt();
											}
											break;
										case 7:

											if (listaTurismos.containsKey(matriculaOriginal)) {
												System.out.println("Ingresa identificador del extra");
												extra = sc.nextInt();
												extraTurismo = extraLeer.buscarExtra(extra);
											} else {
												break;
											}
											break;
										}// fin switch
										if (listaCamiones.containsKey(matriculaOriginal)) {
											Camion camionModificarFinal = new Camion(matricula, marca, modelo, color,
													precio, capacidadCarga);
											camionModificarFinal.modificarCamion(matriculaOriginal);
										}
										if (listaTurismos.containsKey(matriculaOriginal)) {
											Turismo turismoModificarFinal = new Turismo(matricula, marca, modelo, color,
													precio, numPuertas, extraTurismo);
											turismoModificarFinal.modificarTurismo(matriculaOriginal);
										}

									} catch (InputMismatchException e) {
										System.err.println("No se pueden introducir letras");
									}
								} else {
									System.err.println("La matricula no esta registrada");
									matriculaOK = false;
								}
							} while (!matriculaOK);
							break;
						case 5:
							// eliminar vehiculo
							listaTurismos = turismoLeer.leerTodosTurismos();
							listaCamiones = camionLeer.leerTodosCamiones();
							boolean eliminarVehiculoOK = false;
							do {
								System.out.println("Introduce la matricula del vehiculo que quieres eliminar");
								String matricula = sc.next();
								Camion camionEliminar = new Camion();
								Turismo turismoEliminar = new Turismo();
								if (listaCamiones.containsKey(matricula)) {
									System.out.println(listaCamiones.get(matricula).toString());
									camionEliminar = camionEliminar.buscarCamion(matricula);
									camionEliminar.eliminarCamion();
									System.out.println("Camion eliminado");
									eliminarVehiculoOK = true;
								} else if (listaTurismos.containsKey(matricula)) {
									System.out.println(listaTurismos.get(matricula).toString());
									turismoEliminar = turismoEliminar.buscarTurismo(matricula);
									turismoEliminar.eliminarTurismo();
									System.out.println("Turismo eliminado");
									eliminarVehiculoOK = true;
								} else {
									System.err.println("La matricula no esta registrada");
									eliminarVehiculoOK = false;
								}
							} while (!eliminarVehiculoOK);
							break;

						case 6:
							// leer todos los extras
							listaExtras = extraLeer.leerTodosExtras();
							System.out.println(listaExtras.toString());
							break;
						case 7:
							// annadir un nuevo extra
							listaExtras = extraLeer.leerTodosExtras();
							boolean annadirOK;
							try {
								do {
									System.out.println("Introduzca el identificador");
									int identificador = sc.nextInt();
									System.out.println("Introduzca la descripcion");
									String descripcion=sc.next();
									descripcion += sc.nextLine();
									Extra extraAnnadir = new Extra(identificador, descripcion);
									annadirOK = extraAnnadir.annadirExtra();

								} while (!annadirOK);
							} catch (InputMismatchException e) {
								System.err.println("No se pueden introducir letras en la capacidad de carga ");
							}

							break;
						case 8:
							// eliminar un extra
							listaExtras = extraLeer.leerTodosExtras();
							boolean eliminarExtraOK = false;
							try {
								do {
									System.out.println("Introduce el identificador del extra que quieres eliminar");
									int identificador = sc.nextInt();
									Extra extraEliminar = extraLeer.buscarExtra(identificador);
									eliminarExtraOK = extraEliminar.eliminarExtra();
								} while (!eliminarExtraOK);
							} catch (InputMismatchException e) {
								System.err.println("No se pueden introducir letras");
							}

							break;
						case 9:
							// cerrar sesion
							cerrarSesion = true;
							loginCorrecto = false;

							System.out.println("Sesion cerrada");
							break;

						}// fin switch

					} while (!cerrarSesion);

				} catch (InputMismatchException e) {
					e.printStackTrace();
					System.err.println("No se pueden introducir letras switch");
				}
			} while (cerrarSesion);
		}// fin main

}
