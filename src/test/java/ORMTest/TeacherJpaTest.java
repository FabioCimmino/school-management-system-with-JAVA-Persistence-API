package ORMTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.entities.Clas;
import model.entities.Subject;
import model.entities.Teacher;
import model.services.ClassService;
import model.services.SubjectService;
import model.services.TeacherService;

public class TeacherJpaTest {

	@Before
	@After
	public void cleanTables() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("Assignment3").createEntityManager();
		
		String query1 = "DELETE FROM TEACHER_CLAS";
		String query2 = "DELETE FROM TEACHER_SUBJECT";
		String query3 = "DELETE FROM TEACHER";
		
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(query1).executeUpdate();
		entityManager.createNativeQuery(query2).executeUpdate();
		entityManager.createNativeQuery(query3).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testCreate() {
		//Creo a aggiungo una materia al db
		Subject s1 = new Subject ("Italiano",null);
		SubjectService subjectService= new SubjectService();
		subjectService.saveSubject(s1);
		
		//Creo a aggiungo una classe al db
		Clas classe1= new Clas ("Quarta",null);
		ClassService classService = new ClassService();
		classService.saveClass(classe1);
		
		//Creo e aggiungo un insegnante al db
		ArrayList<Clas> listaClassi = new ArrayList<Clas>();
		listaClassi.add(classe1);
		ArrayList<Subject> listaSubject = new ArrayList<Subject>();
		listaSubject.add(s1);
		Teacher t1 = new Teacher ("Prof. Bianchi",listaClassi, listaSubject);
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		
		List <Teacher> getTeacher = new ArrayList <Teacher> ();
		getTeacher = teacherService.getAllTeacher();
		assertEquals(1,getTeacher.size());
		assertEquals("Prof. Bianchi",teacherService.getTeacherById(t1.getTeacherId()).getTeacherName());
		
	}
	
	@Test
	public void testRead () {
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		assertEquals("Prof. Bianchi",teacherService.getTeacherById(t1.getTeacherId()).getTeacherName());
	}
	
	@Test
	public void testUpdate () {
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		assertEquals("Prof. Bianchi",teacherService.getTeacherById(t1.getTeacherId()).getTeacherName());
		t1.setTeacherName("TestTest");
		teacherService.updateTeacher(t1);
		assertEquals("TestTest",teacherService.getTeacherById(t1.getTeacherId()).getTeacherName());
	}

	@Test
	public void testDelete () {
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		
		List <Teacher> getTeacher= new ArrayList <Teacher> ();
		getTeacher = teacherService.getAllTeacher();
		assertEquals(1,getTeacher.size());
		
		teacherService.deleteTeacher(t1.getTeacherId());
		getTeacher = teacherService.getAllTeacher();
		assertEquals(0,getTeacher.size());
		assertEquals(null,teacherService.getTeacherById(t1.getTeacherId()));
		
	}
	
	@Test
	public void testQuery () {
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		List <Teacher> getTeacher= new ArrayList <Teacher> ();
		getTeacher = teacherService.getSelectWhere("teacherName", "Prof. Bianchi");
		assertEquals (1,getTeacher.size());
		assertEquals ("Prof. Bianchi",getTeacher.get(0).getTeacherName());
		
		
	}
}
