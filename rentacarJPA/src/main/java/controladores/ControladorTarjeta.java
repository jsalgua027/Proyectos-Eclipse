package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Alquiler;
import entidades.TarjetaBancaria;

public class ControladorTarjeta {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rentacar");
	private EntityManager em;
	private Query consulta;

	public void crearTarjeta(TarjetaBancaria t) {
		// Crea el entity manager
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir (guardar) información en la misma
		this.em.getTransaction().begin();
		// Con persist() se guarda el objeto t en el contexto de persistencia 
		// (caché intermedia), por lo que t es una entidad conectada
		this.em.persist(t);
		// Con commit se vuelca la información del contexto (caché intermedia) 
		// en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}
	
	public void borrarTarjeta(TarjetaBancaria t) {
		this.em = entityManagerFactory.createEntityManager();
		TarjetaBancaria aux = null;
		// Inicia una transacción con la BD porque se va a realizar una operación de 
		// borrado 
		this.em.getTransaction().begin();
		// Si t no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(t)) {
			// Carga t en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(t);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché (contexto de persistencia)
		this.em.remove(aux);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	public void modificarTarjeta(TarjetaBancaria t) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir (guardar) información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que v existe en el
		// contexto de persistencia (caché), se actualizan sus atributos
		// Si el objeto t no existe en la base de datos, se comporta como persist()
		this.em.merge(t);
		// Vuelca los cambios desde la caché a la base de datos
		this.em.getTransaction().commit();
		this.em.close();

	}

	public TarjetaBancaria findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		TarjetaBancaria aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from tarjeta_bancaria where id = ?", TarjetaBancaria.class);
		// Al igual que con JDBC, se le indica a la query el parámetro
		this.consulta.setParameter(1, pk);
		
		try {
			// Se ejecuta la consulta
			aux = (TarjetaBancaria) consulta.getSingleResult();
		} catch (NoResultException nre) { // Si no hay resultados	
			aux = null;
		}
		
		this.em.close();
		return aux;

	}
	
	public TarjetaBancaria findByNumero(String numero) {
		this.em = entityManagerFactory.createEntityManager();
		TarjetaBancaria aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from tarjeta_bancaria where numero = ?", TarjetaBancaria.class);
		this.consulta.setParameter(1, numero);

		try {
			aux = (TarjetaBancaria) consulta.getSingleResult();
		} catch (NoResultException nre) {	
			aux = null;
		}
		this.em.close();
		return aux;

	}

	public List<TarjetaBancaria> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("TarjetaBancaria.findAll");
		List<TarjetaBancaria> listaTarjetas = (List<TarjetaBancaria>) consulta.getResultList();
		this.em.close();
		return listaTarjetas;
	}

}
