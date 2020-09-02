package ORMTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.entities.Exam;
import model.entities.Subject;
import model.services.ExamService;
import model.services.SubjectService;

public class ExamJpaTest {

	@Before
	@After
	public void cleanTables() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("Assignment3").createEntityManager();
		
		String query1 = "DELETE FROM EXAM";
		String query2 = "DELETE FROM SUBJECT";
		
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(query1).executeUpdate();
		entityManager.createNativeQuery(query2).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testCreate() {
		//Creo a aggiungo degli esami nel db
		Exam ex1= new Exam ("18/09/2018",8,null);
		Exam ex2= new Exam ("18/09/2018",3,null);
		Exam ex3= new Exam ("18/09/2018",7,null);
		Exam ex4= new Exam ("18/09/2018",10,null);
		Exam ex5= new Exam ("18/09/2018",5,null);
		
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		examService.saveExam(ex2);
		examService.saveExam(ex3);
		examService.saveExam(ex4);
		examService.saveExam(ex5);
		
		//Creo delle materie da associare agli esami
		Subject s1= new Subject ("Italiano",null);
		Subject s2= new Subject ("Matematica",null);
		Subject s3= new Subject ("Storia",null);
		
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		subjectService.saveSubject(s2);
		subjectService.saveSubject(s3);
		
		ex1.setSubject(s1);
		ex2.setSubject(s2);
		ex3.setSubject(s3);
		ex4.setSubject(s1);
		ex5.setSubject(s2);
		
		//aggiorno gli esami nel db
		examService.updateExam(ex1);
		examService.updateExam(ex2);
		examService.updateExam(ex3);
		examService.updateExam(ex4);
		examService.updateExam(ex5);
		examService.updateExam(ex1);
		
		List getExam = new ArrayList <Exam>();
		getExam= examService.getAllExams();
		assertEquals(5,getExam.size());
		assertEquals(8,examService.getExamById(ex1.getExamId()).getExamVote());
		assertEquals(3,examService.getExamById(ex2.getExamId()).getExamVote());
		assertEquals(7,examService.getExamById(ex3.getExamId()).getExamVote());
		assertEquals(10,examService.getExamById(ex4.getExamId()).getExamVote());
		assertEquals(5,examService.getExamById(ex5.getExamId()).getExamVote());
		
	}
	
	@Test
	public void testRead () {
		Exam ex1= new Exam ("Date test",8,null);
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		assertEquals("Date test",examService.getExamById(ex1.getExamId()).getExamDate());
	}
	
	@Test
	public void testUpdate () {
		Exam ex1= new Exam ("Date test",8,null);
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		ex1.setExamVote(4);
		examService.updateExam(ex1);;
		assertEquals(4,examService.getExamById(ex1.getExamId()).getExamVote());
	}
	
	@Test
	public void testDelete () {
		Exam ex1= new Exam ("Date test",8,null);
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		
		Subject s1= new Subject ("Italiano",null);
		
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		
		ex1.setSubject(s1);
		
		examService.updateExam(ex1);
		
		examService.deleteExam(ex1.getExamId());
		
		List getExam = new ArrayList <Exam>();
		getExam= examService.getAllExams();
		assertEquals(0,getExam.size());
		
		List getSubject = new ArrayList <Subject>();
		getSubject= subjectService.getAllSubject();
		assertEquals(1,getSubject.size());
	}
	
	@Test
	public void testQuery () {
		Exam ex1= new Exam ("09/01/2019",8,null);
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		
		List <Exam> getExam = new ArrayList <Exam> ();
		getExam = examService.getSelectWhere("examDate", "09/01/2019");
		assertEquals(1,getExam.size());
		assertEquals(8,getExam.get(0).getExamVote());
	}

}
