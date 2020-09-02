package model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Student {

@Id
@GeneratedValue( strategy= GenerationType.AUTO ) 

private int studentId;
private String studentName;
private int studentAge;

@ManyToOne(targetEntity=Clas.class,cascade={CascadeType.MERGE})
private Clas clas;

@OneToMany( targetEntity=Exam.class,cascade={CascadeType.MERGE},orphanRemoval=true)
private List examlist;

@OneToMany(targetEntity=Student.class, cascade={CascadeType.MERGE},orphanRemoval=true )
private List parentlist;

public Student( ) {
    super();
 }

public Student(String studentName, int studentAge, Clas clas, List examlist, List parentlist) {
	super();
	this.studentName = studentName;
	this.studentAge = studentAge;
	this.clas = clas;
	this.examlist = examlist;
	this.parentlist = parentlist;
}

public int getStudentId() {
	return studentId;
}

public void setStudentId(int studentId) {
	this.studentId = studentId;
}

public String getStudentName() {
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public int getStudentAge() {
	return studentAge;
}

public void setStudentAge(int studentAge) {
	this.studentAge = studentAge;
}

public Clas getClas() {
	return clas;
}

public void setClas(Clas clas) {
	this.clas = clas;
}

public List getExamlist() {
	return examlist;
}

public void setExamlist(List examlist) {
	this.examlist = examlist;
}

public List getParentlist() {
	return parentlist;
}

public void setParentlist(List parentlist) {
	this.parentlist = parentlist;
}




}
