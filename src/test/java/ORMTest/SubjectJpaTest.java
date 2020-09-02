package ORMTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.entities.Subject;
import model.entities.Teacher;
import model.services.SubjectService;
import model.services.TeacherService;

public class SubjectJpaTest {

	@Before
	@After
	public void cleanTables() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("Assignment3").createEntityManager();
		
		String query3 = "DELETE FROM SUBJECT_TEACHER";
		String query1 = "DELETE FROM SUBJECT";
		String query2 = "DELETE FROM TEACHER";
		
		
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(query3).executeUpdate();
		entityManager.createNativeQuery(query1).executeUpdate();
		entityManager.createNativeQuery(query2).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testCreate() {
		//Creo e salvo degli insegnanti nel db
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		Teacher t2 = new Teacher ("Prof. Rossi",null,null);
		Teacher t3 = new Teacher ("Prof. Verdi",null,null);
		
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		teacherService.saveTeacher(t2);
		teacherService.saveTeacher(t3);
		
		//Creo liste di insegnanti e le associo alla materia
		List<Teacher> teacherS1 = new ArrayList <Teacher> ();
		teacherS1.add(t1);
		teacherS1.add(t2);
		teacherS1.add(t3);
		
		//Creo la materia e la aggiungo al db
		Subject s1 = new Subject ("Italiano",teacherS1);
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		
		List getSubject = new ArrayList <Subject>();
		getSubject= subjectService.getAllSubject();
		assertEquals(1,getSubject.size());
	}
	
	@Test
	public void testRead () {
		Subject s1 = new Subject ("TestMateria",null);
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		assertEquals("TestMateria",subjectService.getSubjectById(s1.getSubjectId()).getSubjectName());
	}
	
	@Test
	public void testUpdate () {
		Subject s1 = new Subject ("TestMateria",null);
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		s1.setSubjectName("TestTest");
		subjectService.updateSubject(s1);
		assertEquals("TestTest",subjectService.getSubjectById(s1.getSubjectId()).getSubjectName());
	}

	
	@Test
	public void testDelete () {
		Subject s1 = new Subject ("TestMateria",null);
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		subjectService.deleteSubject(s1.getSubjectId());
		List getSubject = new ArrayList <Subject>();
		getSubject= subjectService.getAllSubject();
		assertEquals(0,getSubject.size());
		assertEquals(null,subjectService.getSubjectById(s1.getSubjectId()));
	}
	
	@Test
	public void testQuery () {
		Subject s1 = new Subject ("TestMateria",null);
		SubjectService subjectService = new SubjectService();
		subjectService.saveSubject(s1);
		
		List <Subject> getSubject = new ArrayList <Subject>();
		getSubject = subjectService.getSelectWhere("subjectName", "TestMateria");
		assertEquals (1,getSubject.size());
		assertEquals("TestMateria",getSubject.get(0).getSubjectName());
	}
}
