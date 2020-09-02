package model.services;

import java.util.List;

import model.entities.Teacher;
import model.manager.Dao;
import model.manager.TeacherDaoJpaImpl;

public class TeacherService {

	 private Dao <Teacher> dao;
	  
	  
	  public TeacherService() {
			dao = new TeacherDaoJpaImpl();
			
		}

	public void saveTeacher(Teacher teacher) {
	      try {
	          dao.save(teacher);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public List<Teacher> getAllTeacher() {
	      try {
	          return dao.loadAll();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public Teacher getTeacherById(int id) {
	      try {
	          return dao.load(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public void deleteTeacher (int id) {
	      try {
	          dao.delete(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public void updateTeacher(Teacher teacher) {
	      try {
	          dao.update(teacher);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<Teacher> getSelectWhere(String field, String value) {
	      try {
	          return dao.selectWhere(field, value);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }
}
