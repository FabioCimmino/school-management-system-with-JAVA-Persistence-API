package model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.entities.Student;

public class StudentDaoJpaImpl implements Dao <Student>{

	
	  private static EntityManagerFactory emf;
	
	  static {
		  emf = Persistence.createEntityManagerFactory( "Assignment3" );
	  }
	  
	  public StudentDaoJpaImpl() {
		super();
	}

	@Override
	  public void save(Student student) {
	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.persist(student);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public Student load(int id) {
	      EntityManager em = emf.createEntityManager();
	      Student student = em.find(Student.class, id);
	      em.close();
	      return student;
	  }

	  @Override
	  public void delete(int id) {
	      EntityManager em = emf.createEntityManager();
	      Student student = em.find(Student.class, id);

	      em.getTransaction().begin();
	      em.remove(student);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public void update(Student person) {

	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.merge(person);
	      em.getTransaction().commit();
	      em.close();
	  }

	 @SuppressWarnings("unchecked")
	@Override
	  public List <Student> loadAll() {
	      EntityManager em = emf.createEntityManager();
	      List<Student> persons = em.createQuery("Select t from Student t").getResultList();
	      em.close();
	      return persons;
	  }
	 
	 @Override
	 @SuppressWarnings("unchecked")
	public List<Student> selectWhere(String field, String value) {
	        return emf.createEntityManager()
	                .createQuery("SELECT student FROM Student student WHERE student." + field + " LIKE :value")
	                .setParameter("value", value)
	                .getResultList();
	    }


}
