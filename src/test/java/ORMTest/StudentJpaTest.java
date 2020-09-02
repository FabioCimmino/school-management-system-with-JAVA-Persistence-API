package ORMTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import model.entities.Clas;
import model.entities.Exam;
import model.entities.Student;
import model.services.*;

public class StudentJpaTest {

	@Before
	@After
	public void cleanTables() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("Assignment3").createEntityManager();
		
		String query1 = "DELETE FROM STUDENT_EXAM";
		String query2 = "DELETE FROM STUDENT_STUDENT";
		String query3 = "DELETE FROM STUDENT";
		
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(query1).executeUpdate();
		entityManager.createNativeQuery(query2).executeUpdate();
		entityManager.createNativeQuery(query3).executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	
	@Test
	public void testCreate() {
		//Creo 5 studenti
		Student stud1= new Student ("Fabio",22,null,null,null);
		Student stud2= new Student ("Mario",17,null,null,null);
		Student stud3= new Student ("Luisa",24,null,null,null);
		Student stud4= new Student ("Alice",19,null,null,null);
		Student stud5= new Student ("Erica",20,null,null,null);
		
		StudentService studentService = new StudentService();
		studentService.saveStudent(stud1);
		studentService.saveStudent(stud2);
		studentService.saveStudent(stud3);
		studentService.saveStudent(stud4);
		studentService.saveStudent(stud5);
		
		//Creo e aggiungo le classi nel db
		Clas class1= new Clas ("Prima",null);
		Clas class2 = new Clas ("Seconda", null);
		Clas class3 = new Clas ("Terza",null);
		
		ClassService classService= new ClassService();
		classService.saveClass(class1);
		classService.saveClass(class2);
		classService.saveClass(class3);
		
		//Assegno ad ogni studente una classe
		stud1.setClas(class1);
		stud2.setClas(class1);
		stud3.setClas(class2);
		stud4.setClas(class2);
		stud5.setClas(class3);
		
		//Creo le liste dei parenti di ogni studente
		
		List<Student> parentList1 = new ArrayList ();
		parentList1.add(stud2);
		parentList1.add(stud3);
						
		List<Student> parentList2 = new ArrayList ();
		parentList2.add(stud1);
		parentList2.add(stud3);
						
		List<Student> parentList3 = new ArrayList ();
		parentList3.add(stud1);
		parentList3.add(stud2);
						
		List<Student> parentList4 = new ArrayList ();
		parentList4.add(stud5);
						
		List<Student> parentList5 = new ArrayList ();
		parentList5.add(stud4);
						
		//Associo ad ogni studenti i suoi parenti
		stud1.setParentlist(parentList1);
		stud2.setParentlist(parentList2);
		stud3.setParentlist(parentList3);
		stud4.setParentlist(parentList4);
		stud5.setParentlist(parentList5);
		
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
		
		//Creo liste di esami di ogni studente
		List <Exam> listExam1 = new ArrayList();
		listExam1.add(ex1);
				
		List <Exam> listExam2 = new ArrayList();
		listExam2.add(ex2);
				
		List <Exam> listExam3 = new ArrayList();
		listExam3.add(ex3);
				
		List <Exam> listExam4 = new ArrayList();
		listExam4.add(ex4);
				
		List <Exam> listExam5 = new ArrayList();
		listExam5.add(ex5);
				
				
		//Associo ad ogni studente gli esami sostenuti
		stud1.setExamlist(listExam1);
		stud2.setExamlist(listExam2);
		stud3.setExamlist(listExam3);
		stud4.setExamlist(listExam4);
		stud5.setExamlist(listExam5);
				
		//Aggiungo le modifiche degli studenti nel db
				
		studentService.updateStudent(stud1);
		studentService.updateStudent(stud2);
		studentService.updateStudent(stud3);
		studentService.updateStudent(stud4);
		studentService.updateStudent(stud5);
				
		List getStudent = new ArrayList <Student> ();
		getStudent= studentService.getAllStudent();
		assertEquals(5,getStudent.size());
		
		assertEquals("Fabio",studentService.getStudentById(stud1.getStudentId()).getStudentName());
		assertEquals("Mario",studentService.getStudentById(stud2.getStudentId()).getStudentName());
		assertEquals("Luisa",studentService.getStudentById(stud3.getStudentId()).getStudentName());
		assertEquals("Alice",studentService.getStudentById(stud4.getStudentId()).getStudentName());
		assertEquals("Erica",studentService.getStudentById(stud5.getStudentId()).getStudentName());
				
	}
	
	@Test
	public void testRead () {
		Student studRead= new Student ("Marco",18,null,null,null);
		StudentService studService= new StudentService();
		studService.saveStudent(studRead);
		assertEquals("Marco",studService.getStudentById(studRead.getStudentId()).getStudentName());
	}
	
	@Test 
	public void testUpdate () {
		Student studUpdate = new Student ("Marco",18,null,null,null);
		StudentService studService= new StudentService();
		studService.saveStudent(studUpdate);
		
		studUpdate.setStudentAge(22);
		studUpdate.setStudentName("Test");
		studService.updateStudent(studUpdate);
		
		assertEquals(22,studService.getStudentById(studUpdate.getStudentId()).getStudentAge());
		assertEquals("Test",studService.getStudentById(studUpdate.getStudentId()).getStudentName());
	}
	
	@Test 
	public void testDelete () {
		Student studDelete = new Student ("Elisa",15,null,null,null);
		StudentService studService= new StudentService();
		studService.saveStudent(studDelete);
		
		Clas class1= new Clas ("Prima",null);
		ClassService classService= new ClassService();
		classService.saveClass(class1);
		
		studDelete.setClas(class1);
		
		
		Exam ex1= new Exam ("18/09/2018",8,null);
		Exam ex2= new Exam ("18/09/2018",3,null);
		Exam ex3= new Exam ("18/09/2018",7,null);
		ExamService examService= new ExamService();
		examService.saveExam(ex1);
		examService.saveExam(ex2);
		examService.saveExam(ex3);
		List <Exam> listExam1 = new ArrayList();
		listExam1.add(ex1);
		listExam1.add(ex2);
		listExam1.add(ex3);
		
		studDelete.setExamlist(listExam1);
		studService.updateStudent(studDelete);
		
		studService.deleteStudent(studDelete.getStudentId());
		
		List getStudent = new ArrayList <Student> ();
		getStudent= studService.getAllStudent();
		assertEquals(0,getStudent.size());
		assertEquals(null,studService.getStudentById(studDelete.getStudentId()));
	}
	
	@Test
	public void testQuery () {
		Student stud = new Student ("Elisa",15,null,null,null);
		StudentService studService= new StudentService();
		studService.saveStudent(stud);	
		
		List <Student> getStudent = new ArrayList <Student>();
		getStudent = studService.getSelectWhere("studentName", "Elisa");
		assertEquals(1,getStudent.size());
		assertEquals(15, getStudent.get(0).getStudentAge());
	}

}
