package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 *  IMPORTANTE PRIMERO RESTAURANTE DESPUES ENCARGADO DESPUES CLINTE A LA HORA DE INICIARLO
 * The persistent class for the encargado database table.
 * 
 */
@Entity
@NamedQuery(name="Encargado.findAll", query="SELECT e FROM Encargado e")
public class Encargado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codEncar;

	private int diasTraba;

	private String nomEncar;

	private String numTelEnca;

	//bi-directional many-to-one association to Restaurante
	@OneToMany(mappedBy="encargado")
	private List<Restaurante> restaurantes;

	public Encargado() {
	}

	public int getCodEncar() {
		return this.codEncar;
	}

	public void setCodEncar(int codEncar) {
		this.codEncar = codEncar;
	}

	public int getDiasTraba() {
		return this.diasTraba;
	}

	public void setDiasTraba(int diasTraba) {
		this.diasTraba = diasTraba;
	}

	public String getNomEncar() {
		return this.nomEncar;
	}

	public void setNomEncar(String nomEncar) {
		this.nomEncar = nomEncar;
	}

	public String getNumTelEnca() {
		return this.numTelEnca;
	}

	public void setNumTelEnca(String numTelEnca) {
		this.numTelEnca = numTelEnca;
	}

	public List<Restaurante> getRestaurantes() {
		return this.restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Restaurante addRestaurante(Restaurante restaurante) {
		getRestaurantes().add(restaurante);
		restaurante.setEncargado(this);

		return restaurante;
	}

	public Restaurante removeRestaurante(Restaurante restaurante) {
		getRestaurantes().remove(restaurante);
		restaurante.setEncargado(null);

		return restaurante;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Número de encragado =");
		builder.append(codEncar);
		builder.append(", Días Trabajados =");
		builder.append(diasTraba);
		builder.append(", Nombre =");
		builder.append(nomEncar);
		builder.append(", Teléfono =");
		builder.append(numTelEnca);
		builder.append(", del restaurante =");
		builder.append(restaurantes);
		builder.append("]");
		return builder.toString();
	}

}