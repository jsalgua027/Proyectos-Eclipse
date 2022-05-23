package pruebas;

import java.sql.Date;
import java.time.LocalDate;

import controladores.ControladorCliente;
import controladores.ControladorTarjeta;
import entidades.Cliente;
import entidades.TarjetaBancaria;

public class PruebaControladorCliente {

	public static void main(String[] args) {

		ControladorCliente cc = new ControladorCliente();
		ControladorTarjeta ct = new ControladorTarjeta();

		// Se imprimen todos los registros que haya en la tabla cliente
		imprimirEntidades(cc);

		// Creación de un cliente, sin alquileres ni tarjeta
		Cliente c1 = new Cliente();
		c1.setNombre("Princesa");
		c1.setApellidos("Leia");
		c1.setNif("111111");

		cc.crearCliente(c1);
		imprimirEntidades(cc);

		// Creación de un cliente sin alquileres y con tarjeta
		// Se crea la tarjeta del cliente
		TarjetaBancaria tarjeta = new TarjetaBancaria();
		tarjeta.setCsv(222);
		tarjeta.setFechaCaducidad(Date.valueOf(LocalDate.now()));
		tarjeta.setNumero("331166");
		ct.crearTarjeta(tarjeta); 	// Se persiste la tarjeta
		// Se crea el cliente
		Cliente c2 = new Cliente();
		c2.setNombre("Baby");
		c2.setApellidos("Joda");
		c2.setNif("555666");
		// Establece la tarjeta de este cliente, ya que Cliente es propietaria de la 
		// relación
		c2.setTarjetaBancaria(tarjeta); 
		
		// CUIDADO - Si se hace la inserción del cliente 
		// sin haber introducido la tarjeta se genera una excepción
		
		cc.crearCliente(c2);// Se persiste el cliente, una vez la tarjeta está en la BD
		System.out.println("Entidades en Cliente después de introducir Baby Joda");
		imprimirEntidades(cc);
		
		// Obtengo a Baby Joda
		Cliente babyJoda = cc.findByNif("555666");
		System.out.println("Nombre " + babyJoda.getNombre()+ " \n Tarjeta: " + babyJoda.getTarjetaBancaria());

	}

	
	private static void imprimirEntidades(ControladorCliente cc) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Cliente c : cc.findAll()) {
			System.out.println(c);
		}
	}
}
