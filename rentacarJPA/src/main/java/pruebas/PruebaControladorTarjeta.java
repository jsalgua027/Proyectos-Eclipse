package pruebas;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import controladores.ControladorTarjeta;
import entidades.TarjetaBancaria;

public class PruebaControladorTarjeta {

	public static void main(String[] args) {

		ControladorTarjeta ct = new ControladorTarjeta();
		// Se obtienen todas las instancias
		List<TarjetaBancaria> lista = ct.findAll();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (TarjetaBancaria t : lista) {
			System.out.println(t);
		}
		
		// Obtener una entidad por su pk
		System.out.println("Tarjeta id 14 = " + ct.findByPK(14));
		
		// Crear una entidad
		TarjetaBancaria t1 = new TarjetaBancaria();
		t1.setCsv(23222);
		t1.setFechaCaducidad(Date.valueOf(LocalDate.of(2027, Month.MARCH, 5)));
		t1.setNumero("12345678");
		ct.crearTarjeta(t1);

		for (TarjetaBancaria t : ct.findAll()) {
			System.out.println(t);
		}
	
		// Modificar una entidad
		TarjetaBancaria t2 = ct.findByPK(14);
		t2.setCsv(999);
		ct.modificarTarjeta(t2);
		System.out.println("Después de la modificación -------------------- ");
		for (TarjetaBancaria t : ct.findAll()) {
			System.out.println(t);
		}
	
		// Borrar una entidad
		t2 = ct.findByNumero("12345678");
		ct.borrarTarjeta(t2);
		System.out.println("Después del borrado -------------------- ");
		for (TarjetaBancaria t : ct.findAll()) {
			System.out.println(t);
		}
	}

}
