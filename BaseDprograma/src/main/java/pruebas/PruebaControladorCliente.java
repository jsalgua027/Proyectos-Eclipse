package pruebas;

import controladores.ControladorCliente;
import entidades.Cliente;
import entidades.Encargado;
import entidades.Restaurante;

public class PruebaControladorCliente {
	
	private static void imprimirEntidades(ControladorCliente cc) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		
		for (Cliente c : cc.findAll()) {
			System.out.println(c);
		}
	}

	public static void main(String[] args) {
		
		ControladorCliente cc = new ControladorCliente();
		System.out.println("Muestro la lista de clientes añadida a la base de datos cuando se creó con MySQL");
		imprimirEntidades(cc);
		System.out.println("-----------------------METODO DE AGREGAR CLIENTES------------------");
		System.out.println("----------añado dos clientes; el 320 y el 321--------");
		Encargado encar1 = new Encargado();
		Cliente nuevo1 = new Cliente();
		Restaurante rest = new Restaurante();
		
//		Cliente nuevo2 =cc.findByNif("nacho salcedo");
//		System.out.println();
//		System.out.println("Cliente PK = 321: " + nuevo2);
		
		rest.setCodRest(555);
		rest.setDireRest("ooooooooooooooo");
		rest.setEncargado(encar1);
		rest.setNomRest("pppppppppp");
		rest.setNumTelRest("99999999999");
	
		
		
		encar1.setCodEncar(113);
		encar1.setDiasTraba(4);
		encar1.setNomEncar("LOCO");
		encar1.setNumTelEnca("11111111");
		//encar1.setRestaurantes(rest);
		
		nuevo1.setCodClien(320);
		nuevo1.setDirClien("CALLE PERICO DE LOS PALOTES");
		nuevo1.setNomClien("SEBASTIAN ");
		nuevo1.setNumPedido(4571);
		nuevo1.setNumTelClien("859651245");
		nuevo1.setRestaurante(rest);
		
		cc.crearCliente(nuevo1);
		
//		Cliente nuevo2 = new Cliente();
//		nuevo2.setCodClien(321);
//		nuevo2.setDirClien("CALLE LAGUNAS");
//		nuevo2.setNomClien("ANTONIO ");
//		nuevo2.setNumPedido(4572);
//		nuevo2.setNumTelClien("859651458");
//		cc.crearCliente(nuevo2);
//		nuevo2.setRestaurante();
		
		imprimirEntidades(cc);
		

	}

}
