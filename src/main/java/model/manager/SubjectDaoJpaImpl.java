package model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.entities.Subject;

public class SubjectDaoJpaImpl implements Dao<Subject> {

	 private static EntityManagerFactory emf;
		
	  static {
		  emf = Persistence.createEntityManagerFactory( "Assignment3" );
	  }
	  
	  public SubjectDaoJpaImpl() {
		super();
	}

	@Override
	  public void save(Subject subject) {
	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.persist(subject);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public Subject load(int id) {
	      EntityManager em = emf.createEntityManager();
	      Subject subject = em.find(Subject.class, id);
	      em.close();
	      return subject;
	  }

	  @Override
	  public void delete (int id) {
	      EntityManager em = emf.createEntityManager();
	      Subject subject = em.find(Subject.class, id);
	      em.getTransaction().begin();
	      em.remove(subject);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public void update(Subject subject) {

	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.merge(subject);
	      em.getTransaction().commit();
	      em.close();
	  }

	@SuppressWarnings("unchecked")
	@Override
	  public List <Subject> loadAll() {
	      EntityManager em = emf.createEntityManager();
	      List<Subject> subjects = em.createQuery("Select t from Subject t").getResultList();
	      em.close();
	      return subjects;
	  }
	
	 @Override
	 @SuppressWarnings("unchecked")
	public List<Subject> selectWhere(String field, String value) {
	        return emf.createEntityManager()
	                .createQuery("SELECT subject FROM Subject subject WHERE subject." + field + " LIKE :value")
	                .setParameter("value", value)
	                .getResultList();
	    }
}
