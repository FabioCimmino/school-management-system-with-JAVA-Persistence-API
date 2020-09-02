package model.services;

import java.util.List;

import model.entities.Clas;
import model.manager.Dao;
import model.manager.ClasDaoJpaImpl;

public class ClassService {

	  private Dao <Clas> dao;
	  
	  
	  public ClassService() {
			dao = new ClasDaoJpaImpl();
			
		}

	public void saveClass(Clas clas) {
	      try {
	          dao.save(clas);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public List<Clas> getAllClass() {
	      try {
	          return dao.loadAll();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public Clas getCLassById(int id) {
	      try {
	          return dao.load(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public void deleteClass (int id) {
	      try {
	          dao.delete(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public void updateClass(Clas clas) {
	      try {
	          dao.update(clas);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<Clas> getSelectWhere(String field, String value) {
	      try {
	          return dao.selectWhere(field, value);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }
}
