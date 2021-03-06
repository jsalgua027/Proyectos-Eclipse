package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 *  IMPORTANTE PRIMERO RESTAURANTE DESPUES ENCARGADO DESPUES CLINTE A LA HORA DE INICIARLO
 * The persistent class for the restaurante database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurante.findAll", query="SELECT r FROM Restaurante r")
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codRest;

	private String direRest;

	private int encargado_codEncar;

	private String nomRest;

	private String numTelRest;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="restaurante")
	private List<Cliente> Tiposclientes;

	//bi-directional many-to-one association to Encargado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codEncar")
	private Encargado encargado;

	public Restaurante() {
	}

	public int getCodRest() {
		return this.codRest;
	}

	public void setCodRest(int codRest) {
		this.codRest = codRest;
	}

	public String getDireRest() {
		return this.direRest;
	}

	public void setDireRest(String direRest) {
		this.direRest = direRest;
	}

	public int getEncargado_codEncar() {
		return this.encargado_codEncar;
	}

	public void setEncargado_codEncar(int encargado_codEncar) {
		this.encargado_codEncar = encargado_codEncar;
	}

	public String getNomRest() {
		return this.nomRest;
	}

	public void setNomRest(String nomRest) {
		this.nomRest = nomRest;
	}

	public String getNumTelRest() {
		return this.numTelRest;
	}

	public void setNumTelRest(String numTelRest) {
		this.numTelRest = numTelRest;
	}

	public List<Cliente> getTiposclientes() {
		return this.Tiposclientes;
	}

	public void setTiposclientes(List<Cliente> Tiposclientes) {
		this.Tiposclientes = Tiposclientes;
	}

	public Cliente addTiposcliente(Cliente Tiposcliente) {
		getTiposclientes().add(Tiposcliente);
		Tiposcliente.setRestaurante(this);

		return Tiposcliente;
	}

	public Cliente removeTiposcliente(Cliente Tiposcliente) {
		getTiposclientes().remove(Tiposcliente);
		Tiposcliente.setRestaurante(null);

		return Tiposcliente;
	}

	public Encargado getEncargado() {
		return this.encargado;
	}

	public void setEncargado(Encargado encargado) {
		this.encargado = encargado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("N?mero de Restaurante =");
		builder.append(codRest);
		builder.append(", direcci?n Restaurante =");
		builder.append(direRest);
		builder.append(",  C?digo del encargado =");
		builder.append(encargado_codEncar);
		builder.append(", Nombre del Restaurante =");
		builder.append(nomRest);
		builder.append(", Tel?fono Restaurante=");
		builder.append(numTelRest);
		builder.append(", Tiposclientes=");
		builder.append(Tiposclientes);
		builder.append(", encargado=");
		builder.append(encargado);
		builder.append("]");
		return builder.toString();
	}

	
}