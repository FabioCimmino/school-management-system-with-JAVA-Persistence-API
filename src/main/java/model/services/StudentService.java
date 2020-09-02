package model.services;

import java.util.List;

import model.entities.Student;
import model.manager.Dao;
import model.manager.StudentDaoJpaImpl;

public class StudentService {
	  
	  private Dao <Student> dao;
	  
	  
	  public StudentService() {
			dao = new StudentDaoJpaImpl();
			
		}

	public void saveStudent(Student student) {
	      try {
	          dao.save(student);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public List<Student> getAllStudent() {
	      try {
	          return dao.loadAll();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public Student getStudentById(int id) {
	      try {
	          return dao.load(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public void deleteStudent(int id) {
	      try {
	          dao.delete(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public void updateStudent(Student student) {
	      try {
	          dao.update(student);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<Student> getSelectWhere(String field, String value) {
	      try {
	          return dao.selectWhere(field, value);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }
	}
