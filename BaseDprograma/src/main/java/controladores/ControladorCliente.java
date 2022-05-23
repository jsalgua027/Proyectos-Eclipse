package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;

public class ControladorCliente {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BaseDprograma");
	private EntityManager em;
	private Query consulta;

	public void borrarCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		this.em.getTransaction().begin();// se inicia la transacción con la base de datos para el borrado
		// se comprueba que c no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(c)) {
			// carga c en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(c);
		}
		// ahore se puede borrar usando aux, aux es un objeto controlado por la
		// persistencia
		this.em.remove(aux);
		// con el commit se guarda la info del contexto en la base de datos que en este
		// caso es borrar un objeto
		this.em.getTransaction().commit();
		// con el close se cierra el entityManager
		this.em.close();
	}

	public void modificarCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		// merge() comprueba que exita una entidad con el mismo identificador y
		// actualiza los atributos
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		// con persit() se guarda el objeto c en el contexto de persistencia
		this.em.persist(c);
		// con el commit volcamos a la base de datos el objeto
		this.em.getTransaction().commit();
		this.em.close();
	}

	// busqueda por codigo de cliente
	public Cliente findByPK(int codClien) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		// se crea el objeto Query para realizar la consulta
		this.consulta = em.createNativeQuery("Select * from cliente where id = ?", Cliente.class);
		this.consulta.setParameter(1, codClien);
		try {
			// se realiza la consulta
			aux = (Cliente) consulta.getSingleResult();
		} catch (NoResultException nre) { // si no hay resultados
			aux = null;
		}
		this.em.close();
		return aux;

	}
	// busqueda por nombre

	public Cliente findByNif(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		this.consulta = em.createNativeQuery("Select * from cliente where nif = ?", Cliente.class);
		this.consulta.setParameter(1, nombre);
		try {
			aux = (Cliente) consulta.getSingleResult();
		} catch (NoResultException nre) {
			aux = null;
		}
		this.em.close();
		return aux;

	}

	public List<Cliente> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Cliente.findAll");
		List<Cliente> lista = (List<Cliente>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
