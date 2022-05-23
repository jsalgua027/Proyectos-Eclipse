package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * IMPORTANTE PRIMERO RESTAURANTE DESPUES ENCARGADO DESPUES CLINTE A LA HORA DE INICIARLO
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(insertable=false)
	private Integer codClien;

	private String dirClien;

	private String nomClien;

	private int numPedido;

	private String numTelClien;

	//bi-directional many-to-one association to Restaurante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codRest")
	private Restaurante restaurante;

	public Cliente() {
	}

	public Integer getCodClien() {
		return this.codClien;
	}

	public void setCodClien(Integer codClien) {
		this.codClien = codClien;
	}

	public String getDirClien() {
		return this.dirClien;
	}

	public void setDirClien(String dirClien) {
		this.dirClien = dirClien;
	}

	public String getNomClien() {
		return this.nomClien;
	}

	public void setNomClien(String nomClien) {
		this.nomClien = nomClien;
	}

	public int getNumPedido() {
		return this.numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public String getNumTelClien() {
		return this.numTelClien;
	}

	public void setNumTelClien(String numTelClien) {
		this.numTelClien = numTelClien;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente = ");
		builder.append(codClien);
		builder.append(", con la dirección =");
		builder.append(dirClien);
		builder.append(", de nombre =");
		builder.append(nomClien);
		builder.append(", con Numero de pedido =");
		builder.append(numPedido);
		builder.append(", telefono =");
		builder.append(numTelClien);
		builder.append(", restaurante =");
		builder.append(restaurante);
		builder.append("]");
		return builder.toString();
	}
	

}