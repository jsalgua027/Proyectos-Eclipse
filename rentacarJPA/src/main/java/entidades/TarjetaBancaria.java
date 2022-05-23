package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tarjeta_bancaria")
@NamedQuery(name = "TarjetaBancaria.findAll", query = "SELECT t FROM TarjetaBancaria t")

public class TarjetaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int csv;

	// La anotación @Temporal sirve para indicar a JPA el tipo de dato
	// JDBC java.sql (DATE, TIME, TIMESTAMP) al que pasar el atributo
	// "fechaCaducidad",
	// que es de tipo java.util.Date
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_caducidad")
	private Date fechaCaducidad;

	private String numero;

	// Asociación bidireccional entre Tarjeta y Cliente
	// Una tarjeta pertenece a un cliente (@OneToOne)
	// Con este anotación y este atributo se puede recuperar el cliente
	// al que pertenece esta tarjeta.
	@OneToOne(mappedBy = "tarjetaBancaria")
	private Cliente cliente;

	public TarjetaBancaria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCsv() {
		return this.csv;
	}

	public void setCsv(int csv) {
		this.csv = csv;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public LocalDate getFechaCaducidadLocalDate() {
		return new Date(this.fechaCaducidad.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		// Si toString de tarjetaBancaria llama a toString de Cliente
		// se genera una excepción StackOverFlow al desbordarse la pila de llamadas
		// ya que las llamadas se encadenan sin fin
		
		// Para evitar llamadas concatenadas entre objetos relacionados voy
		// a usar el atributo nombre del cliente, no el toString completo.
		String nombre = (this.cliente != null) ? this.cliente.getNombre() : "";

		StringBuilder builder = new StringBuilder();
		builder.append("TarjetaBancaria [id=");
		builder.append(id);
		builder.append(", csv=");
		builder.append(csv);
		builder.append(", fechaCaducidad=");
		builder.append(getFechaCaducidadLocalDate());
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", cliente=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}

}