package pruebas;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import controladores.ControladorAlquiler;
import controladores.ControladorCliente;
import controladores.ControladorTarjeta;
import entidades.Alquiler;
import entidades.Cliente;
import entidades.TarjetaBancaria;
import entidades.Vehiculo;

public class PruebaControladorAlquiler {

	public static void main(String[] args) {
		
		ControladorAlquiler ca = new ControladorAlquiler();

		// Se imprimen todos los registros que haya en la tabla Alquiler
		imprimirEntidades(ca);
		
		// Consulta de alquiler por pk
		Alquiler alquiler = ca.findByPK(1);
		System.out.println();
		System.out.println("Alquiler PK = 1: " + alquiler);

		// En este caso el cliente Bobba Fett (no existe en la bd)
		// alquila un vehículo que tampoco existe en la bd
		// Se persisten todos los objetos con cascade
		
		TarjetaBancaria t1 = new TarjetaBancaria();
		t1.setCsv(959);
		t1.setFechaCaducidad(Date.valueOf(LocalDate.of(2027, Month.MARCH, 5)));
		t1.setNumero("321123000");
		
		Cliente c1 = new Cliente();
		c1.setNombre("Bobba");
		c1.setApellidos("Fett");
		c1.setNif("01010101");
		c1.setTarjetaBancaria(t1); // Se relaciona al cliente con la tarjeta
		
		Vehiculo v1 = new Vehiculo();
		v1.setBastidor("09900990");
		v1.setMatricula("4544DDD");
		v1.setDisponible(true);
		v1.setMarca("Halcón");
		v1.setModelo("Milenario");

		// Creación del alquiler
		Alquiler a1 = new Alquiler();
		a1.setCliente(c1); 			// Se relaciona el alquiler con el cliente c1
		a1.setVehiculo(v1);			// Se relaciona el alquiler con el vehiculo v1
		a1.setFechaInicio(Date.valueOf(LocalDate.of(2022, Month.MARCH, 5)));
		a1.setNumeroDias(30);

		ca.crearAlquiler(a1);
		imprimirEntidades(ca);
	}

	
	private static void imprimirEntidades(ControladorAlquiler ca) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Alquiler a : ca.findAll()) {
			System.out.println(a);
		}
	}
}
