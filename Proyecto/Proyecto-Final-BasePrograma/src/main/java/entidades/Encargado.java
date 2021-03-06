package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the encargado database table.
 * 
 */
@Entity
@Table(name="encargado")
@NamedQuery(name="Encargado.findAll", query="SELECT e FROM Encargado e")
public class Encargado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codEncar;

	private int diasTraba;

	private String nomEncar;

	private String numTelEnca;

	//uni-directional one-to-one association to Restaurante
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codEncar", referencedColumnName="encargado_codEncar", updatable=false, insertable=false)
	private Restaurante restaurante;

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

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}