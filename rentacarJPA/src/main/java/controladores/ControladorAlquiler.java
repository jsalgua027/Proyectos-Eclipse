package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Alquiler;

public class ControladorAlquiler {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rentacar");
	private EntityManager em;
	private Query consulta;

	public void borrarAlquiler(Alquiler c) {
		this.em = entityManagerFactory.createEntityManager();
		Alquiler aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(c)) {
			aux = this.em.merge(c);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarAlquiler(Alquiler c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearAlquiler(Alquiler c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(c);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Alquiler findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Alquiler aux = null;
		this.consulta = em.createNativeQuery("Select * from alquiler where id = ?", Alquiler.class);
		this.consulta.setParameter(1, pk);
		
		try {
			aux = (Alquiler) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}

		this.em.close();
		return aux;

	}

	public List<Alquiler> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Alquiler.findAll");
		List<Alquiler> lista = (List<Alquiler>) consulta.getResultList();
		this.em.close();
		return lista;
	}

}
