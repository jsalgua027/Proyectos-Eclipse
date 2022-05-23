package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;

public class ControladorCliente {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rentacar");
	private EntityManager em;
	private Query consulta;

	public void borrarCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		this.em.getTransaction().begin();
		if (!this.em.contains(c)) {
			aux = this.em.merge(c);
		}
		this.em.remove(aux);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public void modificarCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void crearCliente(Cliente c) {
		this.em = entityManagerFactory.createEntityManager();
		this.em.getTransaction().begin();
		this.em.persist(c);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public Cliente findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		this.consulta = em.createNativeQuery("Select * from cliente where id = ?", Cliente.class);
		this.consulta.setParameter(1, pk);
		try {
			aux = (Cliente) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}
	
	public Cliente findByNif(String nif) {
		this.em = entityManagerFactory.createEntityManager();
		Cliente aux = null;
		this.consulta = em.createNativeQuery("Select * from cliente where nif = ?", Cliente.class);
		this.consulta.setParameter(1, nif);
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
