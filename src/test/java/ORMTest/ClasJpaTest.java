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
import model.entities.Student;
import model.entities.Teacher;
import model.services.ClassService;
import model.services.TeacherService;

public class ClasJpaTest {

	@Before
//	@After
	public void cleanTables() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("Assignment3").createEntityManager();
		
		String query1 = "DELETE FROM TEACHER_CLAS";
		String query2 = "DELETE FROM CLAS_TEACHER";
		String query3 = "DELETE FROM TEACHER";
		String query4=  "DELETE FROM CLAS";
		
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(query1).executeUpdate();
		entityManager.createNativeQuery(query2).executeUpdate();
		entityManager.createNativeQuery(query3).executeUpdate();
		entityManager.createNativeQuery(query4).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	@Test
	public void testCreate() {
		//Creo e salvo delle classi nel db
		Clas classe1 = new Clas ("Prima",null);
		Clas classe2 = new Clas ("Seconda",null);
		Clas classe3 = new Clas ("Terza",null);
		
		ClassService clService = new ClassService();
		clService.saveClass(classe1);
		clService.saveClass(classe2);
		clService.saveClass(classe3);
		
		//Creo e salvo degli insegnanti nel db
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		Teacher t2 = new Teacher ("Prof. Rossi",null,null);
		Teacher t3 = new Teacher ("Prof. Verdi",null,null);
		
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		teacherService.saveTeacher(t2);
		teacherService.saveTeacher(t3);
		
		//creo liste di classi di ogni insegnante ed associo ad ogni insegnante le classi
		List classiT1 = new ArrayList <Clas>();
		classiT1.add(classe1);
		
		List classiT2 = new ArrayList <Clas>();
		classiT2.add(classe1);
		classiT2.add(classe2);
		classiT2.add(classe3);
		
		List classiT3 = new ArrayList <Clas>();
		classiT3.add(classe1);
		classiT3.add(classe3);
		
		t1.setClasSet(classiT1);
		t2.setClasSet(classiT2);
		t3.setClasSet(classiT3);
		teacherService.updateTeacher(t1);
		teacherService.updateTeacher(t2);
		teacherService.updateTeacher(t3);
		
		//Creo liste di insegnanti e le associo alle classi
		
		List teacherC1 = new ArrayList <Teacher> ();
		teacherC1.add(t1);
		teacherC1.add(t2);
		teacherC1.add(t3);
		
		List teacherC2 = new ArrayList <Teacher> ();
		teacherC2.add(t2);
		
		List teacherC3 = new ArrayList <Teacher> ();
		teacherC3.add(t2);
		teacherC3.add(t3);
		
		classe1.setTeacherSet(teacherC1);
		classe2.setTeacherSet(teacherC2);
		classe3.setTeacherSet(teacherC3);
		
		clService.updateClass(classe1);
		clService.updateClass(classe2);
		clService.updateClass(classe3);
		
		List getClass = new ArrayList <Clas>();
		getClass= clService.getAllClass();
		
		assertEquals(3,getClass.size());
		assertEquals("Prima",clService.getCLassById(classe1.getClassId()).getClassName());
		assertEquals("Seconda",clService.getCLassById(classe2.getClassId()).getClassName());
		assertEquals("Terza",clService.getCLassById(classe3.getClassId()).getClassName());
		
	}
	
	@Test
	public void testRead () {
		Clas classe1 = new Clas ("Prima",null);
		ClassService clService= new ClassService();
		clService.saveClass(classe1);
		assertEquals("Prima",clService.getCLassById(classe1.getClassId()).getClassName());
		
	}
	
	@Test 
	public void testUpdate () {
		Clas classe1 = new Clas ("Prima",null);
		ClassService clService= new ClassService();
		clService.saveClass(classe1);
		assertEquals("Prima",clService.getCLassById(classe1.getClassId()).getClassName());
		classe1.setClassName("Test");
		clService.updateClass(classe1);
		assertEquals("Test",clService.getCLassById(classe1.getClassId()).getClassName());
	}

	@Test
	public void testDelete () {
		//Creo e salvo delle classi nel db
		Clas classe1 = new Clas ("Prima",null);
        ClassService clService = new ClassService();
		clService.saveClass(classe1);
		
		//Creo e salvo degli insegnanti nel db
		Teacher t1 = new Teacher ("Prof. Bianchi",null,null);
		Teacher t2 = new Teacher ("Prof. Rossi",null,null);
		Teacher t3 = new Teacher ("Prof. Verdi",null,null);
		
		TeacherService teacherService = new TeacherService();
		teacherService.saveTeacher(t1);
		teacherService.saveTeacher(t2);
		teacherService.saveTeacher(t3);
		
		List teacherC1 = new ArrayList <Teacher> ();
		teacherC1.add(t1);
		teacherC1.add(t2);
		teacherC1.add(t3);
		
		classe1.setTeacherSet(teacherC1);
		clService.updateClass(classe1);
		
		clService.deleteClass(classe1.getClassId());
		
		List getClass = new ArrayList <Clas> ();
		getClass= clService.getAllClass();
		assertEquals(0,getClass.size());
		assertEquals(null,clService.getCLassById(classe1.getClassId()));
		
	}
	
	@Test
	public void testQuery () {
		Clas classe1 = new Clas ("Prima",null);
        ClassService clService = new ClassService();
		clService.saveClass(classe1);
		
		List<Clas> getClasse = new ArrayList <Clas> ();
		getClasse = clService.getSelectWhere("className", "Prima");
		assertEquals (1,getClasse.size());
		assertEquals("Prima",getClasse.get(0).getClassName());
	}
}
