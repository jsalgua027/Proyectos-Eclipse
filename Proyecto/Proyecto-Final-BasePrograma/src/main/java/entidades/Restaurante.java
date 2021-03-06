package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the restaurante database table.
 * 
 */
@Entity
@Table(name="restaurante")
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

}