package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String apellidos;

	private String nif;

	private String nombre;

	// Asociación bidireccional entre Cliente y Alquiler
	// Un cliente puede realizar muchos alquileres
	// Con esta anotación y este atributo se pueden recuperar los alquileres
	// de este cliente
	// mappedBy indica el atributo asociado en la clase Alquiler
	@OneToMany(mappedBy = "cliente")
	private List<Alquiler> alquileresCliente;

	// Asociación bidireccional entre Cliente y TarjetaBancaria
	// Un cliente tiene una tarjeta (@OneToOne) y una tarjeta sólo es de un cliente
	// CascadeType.PERSIST indica que al persistir un cliente persista también la
	// tarjeta asociada
	@OneToOne(cascade = { CascadeType.PERSIST })

	// La anotación JoinColumn indica a JPA el atributo de la tabla
	// Cliente que debe usar para realizar el JOIN con la tabla de TarjetaBancaria
	// Cliente es la entidad propietaria de la relación al tener la clave ajena de
	// Tarjeta
	@JoinColumn(name = "id_tarjeta")
	private TarjetaBancaria tarjetaBancaria;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alquiler> getAlquileresCliente() {
		return this.alquileresCliente;
	}

	public void setAlquileresCliente(List<Alquiler> alquileresCliente) {
		this.alquileresCliente = alquileresCliente;
	}

	// Añade un alquiler a la lista de alquileres del cliente
	public Alquiler addAlquileresCliente(Alquiler alquileresCliente) {
		getAlquileresCliente().add(alquileresCliente);
		alquileresCliente.setCliente(this);

		return alquileresCliente;
	}

	// Borra el alquiler del parámetro de la lista de alquileres del cliente
	public Alquiler removeAlquileresCliente(Alquiler alquileresCliente) {
		getAlquileresCliente().remove(alquileresCliente);
		alquileresCliente.setCliente(null);

		return alquileresCliente;
	}

	public TarjetaBancaria getTarjetaBancaria() {
		return this.tarjetaBancaria;
	}

	public void setTarjetaBancaria(TarjetaBancaria tarjetaBancaria) {
		this.tarjetaBancaria = tarjetaBancaria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [id=");
		builder.append(id);
		builder.append(", apellidos=");
		builder.append(apellidos);
		builder.append(", nif=");
		builder.append(nif);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", alquileresCliente=\n");
		builder.append(toStringAlquileres());
		builder.append(", tarjetaBancaria=\n");
		// Si toString de tarjetaBancaria llama a toString de Cliente
		// se genera una excepción StackOverFlow al desbordarse la pila de llamadas
		// ya que las llamadas se concatenan sin fin
		builder.append(tarjetaBancaria);
		builder.append("]");
		return builder.toString();
	}


	private String toStringAlquileres() {
		StringBuilder tmp = new StringBuilder();
		for (Alquiler alquiler: alquileresCliente) {
			tmp.append("Id: ").append(alquiler.getId());
		}
		return tmp.toString();
	}

}