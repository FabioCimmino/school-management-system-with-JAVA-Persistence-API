package model.services;

import java.util.List;

import model.entities.Exam;
import model.manager.ExamDaoJpaImpl;
import model.manager.Dao;

public class ExamService {

	 private Dao <Exam> dao;
	  
	  
	  public ExamService() {
			dao = new ExamDaoJpaImpl();
			
		}

	public void saveExam(Exam exam) {
	      try {
	          dao.save(exam);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public List<Exam> getAllExams() {
	      try {
	          return dao.loadAll();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public Exam getExamById(int id) {
	      try {
	          return dao.load(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }

	  public void deleteExam (int id) {
	      try {
	          dao.delete(id);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }

	  public void updateExam(Exam exam) {
	      try {
	          dao.update(exam);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<Exam> getSelectWhere(String field, String value) {
	      try {
	          return dao.selectWhere(field, value);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return null;
	  }
}
