package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "alquiler")
@NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a")
public class Alquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "numero_dias")
	private int numeroDias;

	// Relación bidireccional muchos a uno a Cliente
	// Muchos alquileres pueden ser del mismo cliente
	// Este atributo representa el cliente involucrado en este alquiler
	// La tabla Alquiler es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	// Relación bidireccional muchos a uno a Vehiculo
	// Muchos alquileres pueden alquilar al mismo vehiculo
	// Este atributo representa el vehiculo involucrado en este alquiler
	// La tabla Alquiler es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla vehiculo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo vehiculo;

	public Alquiler() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getNumeroDias() {
		return this.numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alquiler [id=");
		builder.append(id);
		builder.append(", fechaInicio=");
		builder.append(fechaInicio);
		builder.append(", numeroDias=");
		builder.append(numeroDias);
		builder.append(", cliente=");
		builder.append(cliente.getId());
		builder.append(", vehiculo=");
		builder.append(vehiculo.getId());
		builder.append("]");
		return builder.toString();
	}

}