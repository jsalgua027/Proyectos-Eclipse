package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="vehiculo")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String bastidor;

	private boolean disponible;

	private String marca;

	private String matricula;

	private String modelo;

	private double precio;

	// Relación bidireccional uno a muchos a Alquiler
	// Un vehículo puede estar alquilado muchas veces
	// Este atributo representa la lista de alquileres en los que
	// se encuentra este vehículo
	// mappedBy indica el atributo asociado en la clase Alquiler 
	@OneToMany(mappedBy="vehiculo")
	private List<Alquiler> alquileresVehiculo;

	public Vehiculo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBastidor() {
		return this.bastidor;
	}

	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}

	public boolean getDisponible() {
		return this.disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Alquiler> getAlquileresVehiculo() {
		return this.alquileresVehiculo;
	}

	public void setAlquileresVehiculo(List<Alquiler> alquileresVehiculo) {
		this.alquileresVehiculo = alquileresVehiculo;
	}

	// Añade un alquiler a este vehículo
	public Alquiler addAlquileresVehiculo(Alquiler alquileresVehiculo) {
		getAlquileresVehiculo().add(alquileresVehiculo);
		alquileresVehiculo.setVehiculo(this);

		return alquileresVehiculo;
	}

	// Borra un alquiler de la lista de alquileres de este vehículo
	public Alquiler removeAlquileresVehiculo(Alquiler alquileresVehiculo) {
		getAlquileresVehiculo().remove(alquileresVehiculo);
		alquileresVehiculo.setVehiculo(null);

		return alquileresVehiculo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehiculo [id=");
		builder.append(id);
		builder.append(", bastidor=");
		builder.append(bastidor);
		builder.append(", disponible=");
		builder.append(disponible);
		builder.append(", marca=");
		builder.append(marca);
		builder.append(", matricula=");
		builder.append(matricula);
		builder.append(", modelo=");
		builder.append(modelo);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", alquileresVehiculo=");
		builder.append(toStringAlquileres());
		builder.append("]");
		return builder.toString();
	}
	
	private String toStringAlquileres() {
		StringBuilder tmp = new StringBuilder();
		for (Alquiler alquiler: alquileresVehiculo) {
			tmp.append("Id: ").append(alquiler.getId());
		}
		return tmp.toString();
	}

}