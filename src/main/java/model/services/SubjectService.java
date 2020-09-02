package model.services;

import java.util.List;

import model.entities.Subject;
import model.manager.Dao;
import model.manager.SubjectDaoJpaImpl;

public class SubjectService {

	 private Dao <Subject> dao;
	  
	  
	  public SubjectService() {
			dao = new SubjectDaoJpaImpl();
			
		}

	public void saveSubject(Subject subject) {
	      try {
	          dao.save(subject);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public List<Subject> getAllSubject() {
	      try {
	          return dao.loadAll();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public Subject getSubjectById(int id) {
	      try {
	          return dao.load(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public void deleteSubject (int id) {
	      try {
	          dao.delete(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public void updateSubject(Subject subject) {
	      try {
	          dao.update(subject);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<Subject> getSelectWhere(String field, String value) {
	      try {
	          return dao.selectWhere(field, value);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }
}
