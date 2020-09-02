package model.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Teacher;

public class TeacherDaoJpaImpl implements Dao <Teacher>{

	 private static EntityManagerFactory emf;
		
	  static {
		  emf = Persistence.createEntityManagerFactory( "Assignment3" );
	  }
	  
	  public TeacherDaoJpaImpl() {
		super();
	}

	@Override
	  public void save(Teacher teacher) {
	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.persist(teacher);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public Teacher load(int id) {
	      EntityManager em = emf.createEntityManager();
	      Teacher teacher = em.find(Teacher.class, id);
	      em.close();
	      return teacher;
	  }

	  @Override
	  public void delete (int id) {
	      EntityManager em = emf.createEntityManager();
	      Teacher teacher = em.find(Teacher.class, id);
	      em.getTransaction().begin();
	      em.remove(teacher);
	      em.getTransaction().commit();
	      em.close();
	  }

	  @Override
	  public void update(Teacher teacher) {

	      EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	      em.merge(teacher);
	      em.getTransaction().commit();
	      em.close();
	  }

	@SuppressWarnings("unchecked")
	@Override
	  public List <Teacher> loadAll() {
	      EntityManager em = emf.createEntityManager();
	      List<Teacher> teachers = em.createQuery("Select t from Teacher t").getResultList();
	      em.close();
	      return teachers;
	  }
	
	 @Override
	 @SuppressWarnings("unchecked")
	public List<Teacher> selectWhere(String field, String value) {
	        return emf.createEntityManager()
	                .createQuery("SELECT teacher FROM Teacher teacher WHERE teacher." + field + " LIKE :value")
	                .setParameter("value", value)
	                .getResultList();
	    }
}
