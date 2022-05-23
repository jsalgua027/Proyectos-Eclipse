package pruebas;

import java.sql.Date;
import java.time.LocalDate;


import controladores.ControladorVehiculo;
import entidades.Vehiculo;


public class PruebaControladorVehiculo {

	public static void main(String[] args) {

		ControladorVehiculo cv = new ControladorVehiculo();

		// Se imprimen todos los registros que haya en la tabla cliente
		imprimirEntidades(cv);

		// Creación de un vehiculo con algunos datos, sin alquileres
		Vehiculo v1 = new Vehiculo();
		v1.setBastidor("44556677");
		v1.setMatricula("4544TTT");
		v1.setDisponible(true);
		v1.setMarca("Mercedes");
		v1.setModelo("A3");
		
		cv.crearVehiculo(v1);
		imprimirEntidades(cv);

	}

	
	private static void imprimirEntidades(ControladorVehiculo cv) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Vehiculo v : cv.findAll()) {
			System.out.println(v);
		}
	}
}
