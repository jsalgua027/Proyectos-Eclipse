package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Encargado;

public class ControladorEncargado {
	
	
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BaseDprograma");
	private EntityManager em;
	private Query consulta;

	
	public void borrarCliente(Encargado e) {
		this.em = entityManagerFactory.createEntityManager();
		Encargado aux = null;
		this.em.getTransaction().begin();// se inicia la transacción con la base de datos para el borrado
		// se comprueba que c no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(e)) {
			//carga c en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(e);
		}
		// ahore se puede borrar usando aux, aux es un objeto controlado por la persistencia
		this.em.remove(aux);
	  // con el commit se guarda la info del contexto en la base de datos que en este caso es borrar un objeto
		this.em.getTransaction().commit();
		// con el close se cierra el entityManager
		this.em.close();
	}
	
	public void modificarCliente(Encargado e) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		//merge() comprueba que exita una entidad con el mismo identificador y actualiza los atributos
		this.em.merge(e);
		this.em.getTransaction().commit();
		this.em.close();

	}
	
	public void crearCliente(Encargado e) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		// con persit() se guarda el objeto c en el contexto de persistencia
		this.em.persist(e);
		// con el commit volcamos a la base de datos el objeto
		this.em.getTransaction().commit();
		this.em.close();
	}
	//busqueda por codigo de cliente
	public Encargado findByPK(int codEncar) {
		this.em = entityManagerFactory.createEntityManager();
		Encargado aux = null;
		// se crea el objeto Query para realizar la consulta
		this.consulta = em.createNativeQuery("Select * from encargado where id = ?", Cliente.class);
		this.consulta.setParameter(1, codEncar);
		try {
			// se realiza la consulta
			aux = (Encargado) consulta.getSingleResult();
		} catch (NoResultException nre) {	// si no hay resultados
			aux = null;
		}
		this.em.close();
		return aux;

	}
	// busqueda por nombre
	
	public Encargado findByNif(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		Encargado aux = null;
		this.consulta = em.createNativeQuery("Select * from encargado where nif = ?", Cliente.class);
		this.consulta.setParameter(1, nombre);
		try {
			aux = (Encargado) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
	
	public List<Encargado> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Encargado.findall");
		List<Encargado> lista = (List<Encargado>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
