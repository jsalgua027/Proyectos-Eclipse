package pruebas;

import java.sql.Date;
import java.time.LocalDate;

import controladores.ControladorCliente;
import controladores.ControladorTarjeta;
import entidades.Cliente;
import entidades.TarjetaBancaria;

public class PruebaControladorClienteCascade {

	public static void main(String[] args) {

		ControladorCliente cc = new ControladorCliente();
		ControladorTarjeta ct = new ControladorTarjeta();

		// Se imprimen todos los registros que haya en la tabla cliente
		//imprimirEntidades(cc);

		// Creación de un cliente sin alquileres y con una tarjeta
		// Se crea la tarjeta del cliente
		TarjetaBancaria tarjeta = new TarjetaBancaria();
		tarjeta.setCsv(345);
		tarjeta.setFechaCaducidad(Date.valueOf(LocalDate.now()));
		tarjeta.setNumero("001199");
		
		// Se crea el cliente
		Cliente cliente = new Cliente();
		cliente.setNombre("Han");
		cliente.setApellidos("Solo");
		cliente.setNif("76559833");
		// Establece la tarjeta de este cliente, ya que Cliente es la entidad propietaria de la 
		// relación
		// Para evitar errores a la hora de actualizar relaciones, el método setCliente()
		// de TarjetaBancaria se puede eliminar en la clase
		cliente.setTarjetaBancaria(tarjeta); 
		
		// Se persiste el cliente. 
		// Al estar anotada la relación con la anotación cascade.PERSIST
		// al persistir el cliente se persiste la tarjeta asociada
		cc.crearCliente(cliente);
		imprimirEntidades(cc);

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
