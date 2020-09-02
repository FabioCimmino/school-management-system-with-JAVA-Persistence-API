package model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Clas;

public class ClasDaoJpaImpl implements Dao <Clas> {
	
	
	  private static EntityManagerFactory emf;
	
	  static {
		  emf = Persistence.createEntityManagerFactory( "Assignment3" );
	  }
	  
	  public ClasDaoJpaImpl() {
		super();
	}

	@Override
	  public void save(Clas clas) {
	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.persist(clas);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public Clas load(int id) {
	      EntityManager em = emf.createEntityManager();
	      Clas clas = em.find(Clas.class, id);
	      em.close();
	      return clas;
	  }

	  @Override
	  public void delete(int id) {
	      EntityManager em = emf.createEntityManager();
	      Clas clas = em.find(Clas.class, id);
	      em.getTransaction().begin();
	      em.remove(clas);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public void update(Clas clas) {

	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.merge(clas);
	      em.getTransaction().commit();
	      em.close();
	  }

	@SuppressWarnings("unchecked")
	@Override
	  public List <Clas> loadAll() {
	      EntityManager em = emf.createEntityManager();
	      List<Clas> classes = em.createQuery("Select t from Clas t").getResultList();
	      em.close();
	      return classes;
	  }
	
	 @Override
	 @SuppressWarnings("unchecked")
	public List<Clas> selectWhere(String field, String value) {
	        return emf.createEntityManager()
	                .createQuery("SELECT clas FROM Clas clas WHERE clas." + field + " LIKE :value")
	                .setParameter("value", value)
	                .getResultList();
	    }

}
