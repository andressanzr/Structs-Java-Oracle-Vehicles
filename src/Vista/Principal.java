package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Modelo.Camion;



class Principal {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Camion ca = new Camion();
		System.out.println(ca.leerTodosCamiones());
		/*
		// Cargar los datos

		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		// Menú principal
		do {
			try {
				System.out.println("Menú");
				System.out.println("1. Mostrar todas las personas");
				System.out.println("2. Buscar una persona");
				System.out.println("3. Añadir una persona");
				System.out.println("4. Modificar una persona");
				System.out.println("5. Eliminar una persona");
				System.out.println("6. Eliminar todas las personas");
				System.out.println("7. Mostrar todas las becas");
				System.out.println("8. Buscar una beca");
				System.out.println("9. Añadir una beca");
				System.out.println("10. Modificar una beca");
				System.out.println("11. Eliminar una beca");
				System.out.println("12. Eliminar todas las becas");
				System.out.println("13. Log out");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					mostrarTodos();
					break;
				case 2:
					buscarPersona(sc);
					break;
				case 3:
					añadirPersona(sc);
					break;
				case 4:
					modificarPersona(sc);
					break;
				case 5:
					eliminarPersona(sc);
					break;
				case 6:
					eliminarTodasLasPersonas(sc);
					break;
				case 7:
					mostrarTodasLasBecas();
					break;
				case 8:
					buscarBeca(sc);
					break;
				case 9:
					AñadirBeca(sc);
					break;
				case 10:
					ModificarBeca(sc);
					break;
				case 11:
					eliminarBeca(sc);
					break;
				case 12:
					eliminarTodaLasBecas(sc);
					break;
				case 13:
					System.out.println("Hasta pronto");
					break;
				default:
					System.out.println("Introduce un número de 1 a 13");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduce un número");
				sc.nextLine();
			}
		} while (opcion != 13);
	}

	public static void eliminarTodaLasBecas(Scanner sc) throws ClassNotFoundException {
		Beca eliminarTodasBecas = new Beca();
		eliminarTodasBecas.eliminarTodo();
		
	}

	public static void eliminarBeca(Scanner sc) throws ClassNotFoundException {
		System.out.println("Indica el Id");
		int id = sc.nextInt();
		Beca delBeca= new Beca();
		delBeca = delBeca.leerBeca(id);
	
		if (delBeca != null) {
			delBeca.eliminar();
		
		} else {
			System.out.printf("No existe la Beca con el Id %d\n", id);
		}		
	}

	public static void ModificarBeca(Scanner sc) throws ClassNotFoundException {
		Beca modBeca = new Beca();

		System.out.println("Indica el Id");
		int id = sc.nextInt();
		modBeca = modBeca.leerBeca(id);
		

		if (modBeca != null ) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. Id");
			System.out.println("2. Cuota");
			System.out.println("3. Descripción");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduce el nuevo Id");
					int Id = sc.nextInt();
					Beca existeBeca= new Beca();
					existeBeca = existeBeca.leerBeca(Id);
				
					if (existeBeca != null) {
						if (existeBeca.getId()==Id) {
							System.out.println("Id repetido");
							repetido = true;
						} 
					} else {
						
							modBeca.setId(Id);					
				}
				} while (repetido);
				break;
			case 2:
				boolean seguir = false;
				do {
					seguir = false;
					try {
						System.out.println("Introduce la nueva cuota");
						double cuota = sc.nextDouble();
							modBeca.setCuota(cuota);


					} catch (InputMismatchException e) {
						System.err.println("Introduzce solo números");
						sc.nextLine();
						seguir = true;
					}
				} while (seguir);
				break;
			case 3:
				System.out.println("Introduce la descripcion");
				sc.nextLine();
				String descripcion = sc.nextLine();
				modBeca.setDescripción(descripcion);				
				break;
			
		
				

			}
			modBeca.actualizar(id);
		} else {
			System.out.printf("No existe la beca con el Id %d\n", id);

		}		
	}

	public static void AñadirBeca(Scanner sc) throws ClassNotFoundException {
		boolean seguir = false;
		int id=0;
		do {
			seguir = false;
			System.out.println("Introduzca el id");
			id = sc.nextInt();
			Beca existeBeca = new Beca();
			existeBeca = existeBeca.leerBeca(id);
			
			if (existeBeca != null) {

				if (existeBeca.getId()==id) {
					System.out.println("Id repetido");
					seguir = true;
				}
			}
		
		} while (seguir);
		double cuota = 0.0;
		do {
			seguir = false;
			try {
				System.out.println("Introduzca la cuota");
				cuota = sc.nextDouble();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
		System.out.println("Introduzca la descripcion");
		sc.nextLine();
		String descripcion = sc.nextLine();
		
			Beca newBeca = new Beca(id, cuota, descripcion);
			newBeca.insertar();
		}
		
	

	public static void buscarBeca(Scanner sc) throws ClassNotFoundException {
		System.out.println("Indica el id");
		int id = sc.nextInt();
		Beca leerBeca = new Beca();
		leerBeca = leerBeca.leerBeca(id);
		
		if (leerBeca != null) {
			System.out.println(leerBeca.toString());
		}  else {
			System.out.printf("No existe la beca con el id %d\n", id);
		}		
	}

	public static void mostrarTodasLasBecas() throws ClassNotFoundException {
		boolean sinPersonas = true;
		Beca buscarBeca = new Beca();
		ArrayList<Beca> becas = buscarBeca.leerTodos();
		for (int i = 0; i < becas.size(); i++) {
			System.out.println(becas.get(i).toString());
			sinPersonas = false;
		}
		
		if (sinPersonas) {
			System.out.println("No existen becas");

		}		
	}

	public static void eliminarTodasLasPersonas(Scanner sc) throws ClassNotFoundException {
		Persona eliminarTodasPersonas = new Profesor();
		eliminarTodasPersonas.eliminarTodo();
		eliminarTodasPersonas = new Alumno();
		eliminarTodasPersonas.eliminarTodo();
	}

	public static void modificarPersona(Scanner sc) throws IOException, ClassNotFoundException {
		Persona modPersona = new Alumno();
		Persona modPersona2 = new Profesor();

		System.out.println("Indica el DNI");
		String DNI = sc.next();
		modPersona = modPersona.leerPersona(DNI);
		modPersona2 = modPersona2.leerPersona(DNI);

		if (modPersona != null || modPersona2 != null) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. DNI");
			System.out.println("2. Nombre");
			System.out.println("3. Apellidos");
			System.out.println("4. Edad");
			if (modPersona != null) {
				System.out.println("5. Beca");
			}
			if (modPersona2 != null) {
				System.out.println("5. Sueldo");
			}
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduce el nuevo DNI");
					String dni = sc.next();
					Persona existePersona = new Alumno();
					existePersona = existePersona.leerPersona(dni);
					Persona existePersona2 = new Profesor();
					existePersona2 = existePersona2.leerPersona(dni);
					if (existePersona != null) {
						if (existePersona.getDNI().equals(dni)) {
							System.out.println("DNI repetido");
							repetido = true;
						} else {
							if (modPersona != null) {
								modPersona.setDNI(dni);
							}

						}
					} else if (existePersona2 != null) {
						if (existePersona2.getDNI().equals(dni)) {
							System.out.println("DNI repetido");
							repetido = true;
						} else {
							if (modPersona2 != null) {
								modPersona2.setDNI(dni);
							}
						}
					} else {

						if (modPersona != null) {
							modPersona.setDNI(dni);
						}
						if (modPersona2 != null) {
							modPersona2.setDNI(dni);
						}
					}
				} while (repetido);
				break;
			case 2:
				System.out.println("Introduce el nuevo nombre");
				String nombre = sc.next();
				modPersona.setNombre(nombre);
				if (modPersona != null) {
					modPersona.setNombre(nombre);
				}
				if (modPersona2 != null) {
					modPersona2.setNombre(nombre);
				}
				break;
			case 3:
				System.out.println("Introduce el nuevo apellido");
				sc.nextLine();
				String apellidos = sc.nextLine();
				if (modPersona != null) {
					modPersona.setApellidos(apellidos);
				}
				if (modPersona2 != null) {
					modPersona2.setApellidos(apellidos);
				}
				break;
			case 4:
				boolean seguir = false;
				do {
					seguir = false;
					try {
						System.out.println("Introduce el nueva edad");
						int edad = sc.nextInt();
						if (modPersona != null) {
							modPersona.setEdad(edad);
						}
						if (modPersona2 != null) {
							modPersona2.setEdad(edad);
						}

					} catch (InputMismatchException e) {
						System.err.println("Introduzce solo números");
						sc.nextLine();
						seguir = true;
					}
				} while (seguir);
				break;
			case 5:
				if (modPersona != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							mostrarTodasLasBecas();
							System.out.println("Introduce el nueva beca");
							int beca = sc.nextInt();
							Beca modBeca = new Beca();
							modBeca=modBeca.leerBeca(beca);
							if(modBeca==null) {
								seguir=true;
								System.out.println("La beca no existe");

							}else {
							((Alumno) modPersona).setBeca(modBeca);
							}
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
				}
				if (modPersona2 != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce el nuevo sueldo");
							double sueldo = sc.nextDouble();
							((Profesor) modPersona2).setSueldo(sueldo);

						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
				}
				break;

			}
			if (modPersona != null) {
				modPersona.actualizar(DNI);

			} else if (modPersona2 != null) {
				modPersona2.actualizar(DNI);

			}

		} else {
			System.out.printf("No existe la persona con el DNI %s\n", DNI);

		}

	}

	public static void eliminarPersona(Scanner sc) throws IOException, ClassNotFoundException {
		System.out.println("Indica el DNI");
		String DNI = sc.next();
		Persona delPersona = new Alumno();
		delPersona = delPersona.leerPersona(DNI);
		Persona delPersona2 = new Profesor();
		delPersona2 = delPersona2.leerPersona(DNI);
		if (delPersona != null) {
			delPersona.eliminar();
		} else if (delPersona2 != null) {
			delPersona2.eliminar();
		} else {
			System.out.printf("No existe la persona con el DNI %s\n", DNI);
		}
	}

	public static void añadirPersona(Scanner sc) throws IOException, ClassNotFoundException {
		boolean seguir = false;
		String DNI = "";
		Beca newBeca = new Beca();
		do {
			seguir = false;
			System.out.println("Introduzca el DNI");
			DNI = sc.next();
			Persona existePersona = new Alumno();
			existePersona = existePersona.leerPersona(DNI);
			Persona existePersona2 = new Profesor();
			existePersona2 = existePersona2.leerPersona(DNI);
			if (existePersona != null) {

				if (existePersona.getDNI().equals(DNI)) {
					System.out.println("DNI repetido");
					seguir = true;
				}
			}
			existePersona = new Profesor();
			existePersona = existePersona.leerPersona(DNI);
			if (existePersona != null) {

				if (existePersona.getDNI().equals(DNI)) {
					System.out.println("DNI repetido");
					seguir = true;
				}
			}
		} while (seguir);
		System.out.println("Introduzca el nombre");
		String nombre = sc.next();
		System.out.println("Introduzca el apellido");
		sc.nextLine();
		String apellidos = sc.nextLine();
		int edad = 0;
		do {
			seguir = false;
			try {
				System.out.println("Introduzca la edad");
				edad = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
		int opcion = 0;
		do {
			try {
				System.out.println("¿Es alumno o profesor?\n1. Alumno\n2. Profesor");
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				sc.nextLine();
			}
		} while (opcion != 1 && opcion != 2);
		int beca = 0;
		if (opcion == 1) {

			do {
				seguir = false;
				try {
					mostrarTodasLasBecas();
					System.out.println("Introduzca la beca");
					beca = sc.nextInt();
					newBeca = new Beca();
					newBeca=newBeca.leerBeca(beca);
					if(newBeca==null) {
						seguir=true;
						System.out.println("La beca no existe");

					}
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			
			Persona newPersona = new Alumno(DNI, nombre, apellidos, edad, newBeca);
			newPersona.insertar();
			
		}
		double sueldo = 0;
		if (opcion == 2) {

			do {
				seguir = false;
				try {
					System.out.println("Introduzca el sueldo");
					sueldo = sc.nextDouble();
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			Persona newPersona = new Profesor(DNI, nombre, apellidos, edad, sueldo);
			newPersona.insertar();
		}

	}

	public static void buscarPersona(Scanner sc) throws ClassNotFoundException {
		System.out.println("Indica el DNI");
		String DNI = sc.next();
		Persona leerPersona = new Alumno();
		leerPersona = leerPersona.leerPersona(DNI);
		Persona leerPersona2 = new Profesor();
		leerPersona2 = leerPersona2.leerPersona(DNI);
		if (leerPersona != null) {
			System.out.println(leerPersona.toString());
		} else if (leerPersona2 != null) {
			System.out.println(leerPersona2.toString());
		} else {
			System.out.printf("No existe la persona con el DNI %s\n", DNI);
		}

	}

	public static void mostrarTodos() throws ClassNotFoundException {
		boolean sinPersonas = true;
		Persona buscarPersona = new Alumno();
		ArrayList<Persona> personas = buscarPersona.leerTodos();
		for (int i = 0; i < personas.size(); i++) {
			System.out.println(personas.get(i).toString());
			sinPersonas = false;
		}
		buscarPersona = new Profesor();
		personas = buscarPersona.leerTodos();
		for (int i = 0; i < personas.size(); i++) {
			System.out.println(personas.get(i).toString());
			sinPersonas = false;
		}
		if (sinPersonas) {
			System.out.println("No existen personas");

		}
		*/
	}

}
