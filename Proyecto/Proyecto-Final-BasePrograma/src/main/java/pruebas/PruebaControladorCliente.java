package pruebas;

import controladores.ControladorCliente;
import controladores.ControladorEncargado;
import controladores.ControladorRestaurante;
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
		ControladorRestaurante rr = new ControladorRestaurante();
		ControladorEncargado ee = new ControladorEncargado();
		
		System.out.println("Muestro la lista de clientes aï¿½adida a la base de datos cuando se creï¿½ con MySQL");
		imprimirEntidades(cc);
		System.out.println("-----------------------METODO DE AGREGAR CLIENTES------------------");
		System.out.println("----------anadidod dos clientes; el 320 y el 321--------");
		
		Encargado encar2 = new Encargado();
		
		encar2.setDiasTraba(4);
		encar2.setNomEncar("PPPPP");
		encar2.setNumTelEnca("9999999");
		
		ee.crearEncargado(encar2);
		
		Restaurante rest = new Restaurante();
		
		rest.setDireRest("AAAAAAAAAAA");
		rest.setEncargado_codEncar(0);
		rest.setNomRest("ÑÑÑÑÑÑÑÑÑ");
		rest.setNumTelRest("555555555");
	
		rr.crearRestaurante(rest);
		
		
		
		
		Cliente nuevo1 = new Cliente();
		
		nuevo1.setDirClien("CALLE PERICO DE LOS PALOTES");
		nuevo1.setNomClien("SEBASTIAN ");
		nuevo1.setNumPedido(4571);
		nuevo1.setNumTelClien("859651245");
		nuevo1.setRestaurante(rest);
		cc.crearCliente(nuevo1);
		
//		Cliente nuevo2 = new Cliente();
//		
//		nuevo2.setDirClien("CALLE LAGUNAS");
//		nuevo2.setNomClien("ANTONIO ");
//		nuevo2.setNumPedido(4572);
//		nuevo2.setNumTelClien("859651458");
//		cc.crearCliente(nuevo2);
//		
		imprimirEntidades(cc);
		

	}

}
