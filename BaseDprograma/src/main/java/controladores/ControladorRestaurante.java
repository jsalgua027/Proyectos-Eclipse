package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Restaurante;

public class ControladorRestaurante {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BaseDprograma");
	private EntityManager em;
	private Query consulta;

	public void borrarCliente(Restaurante r) {
		this.em = entityManagerFactory.createEntityManager();
		Restaurante aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(r)) {
			aux = this.em.merge(r);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarCliente(Restaurante r) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(r);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearCliente(Restaurante r) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(r);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Restaurante findByPK(int codRest) {
		this.em = entityManagerFactory.createEntityManager();
		Restaurante aux = null;
		this.consulta = em.createNativeQuery("Select * from restaurante where id = ?", Cliente.class);
		this.consulta.setParameter(1,codRest);
		try {
			aux = (Restaurante) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
	
	public Restaurante findByNif(String nomRest) {
		this.em = entityManagerFactory.createEntityManager();
		Restaurante aux = null;
		this.consulta = em.createNativeQuery("Select * from restaurante where nif = ?", Cliente.class);
		this.consulta.setParameter(1, nomRest);
		try {
			aux = (Restaurante) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
		// este m?todo seria necesario por si se abren m?s restaurantes poder usar la misma base de datos
	public List<Restaurante> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Cliente.findAll");
		List<Restaurante> lista = (List<Restaurante>) consulta.getResultList();
		this.em.close();
		return lista;
	}

	
	

}
