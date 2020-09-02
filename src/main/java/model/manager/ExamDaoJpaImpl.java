package model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Exam;

public class ExamDaoJpaImpl implements Dao <Exam> {

	 private static EntityManagerFactory emf;
		
	  static {
		  emf = Persistence.createEntityManagerFactory( "Assignment3" );
	  }
	  
	  public ExamDaoJpaImpl() {
		super();
	}

	@Override
	  public void save(Exam exam) {
	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.persist(exam);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public Exam load(int id) {
	      EntityManager em = emf.createEntityManager();
	      Exam exam = em.find(Exam.class, id);
	      em.close();
	      return exam;
	  }

	  @Override
	  public void delete (int id) {
	      EntityManager em = emf.createEntityManager();
	      Exam exam = em.find(Exam.class, id);
	      em.getTransaction().begin();
	      em.remove(exam);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public void update(Exam exam) {

	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.merge(exam);
	      em.getTransaction().commit();
	      em.close();
	  }

	@SuppressWarnings("unchecked")
	@Override
	  public List <Exam> loadAll() {
	      EntityManager em = emf.createEntityManager();
	      List<Exam> exams = em.createQuery("Select t from Exam t").getResultList();
	      em.close();
	      return exams;
	  }

	 @Override
	 @SuppressWarnings("unchecked")
	public List<Exam> selectWhere(String field, String value) {
	        return emf.createEntityManager()
	                .createQuery("SELECT exam FROM Exam exam WHERE exam." + field + " LIKE :value")
	                .setParameter("value", value)
	                .getResultList();
	    }
}
