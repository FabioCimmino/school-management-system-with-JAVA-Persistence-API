package model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Teacher {

   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )
   private int teacherId;
   private String teacherName;

   @ManyToMany(targetEntity = Clas.class,cascade={CascadeType.MERGE})
   private List clasSet;
   
   @ManyToMany(targetEntity = Subject.class, cascade={CascadeType.MERGE})
   private List<Subject> subjectSet;

   public Teacher(){
      super();
   }

public Teacher(String teacherName, List clasSet, List subjectSet) {
	super();
	this.teacherName = teacherName;
	this.clasSet = clasSet;
	this.subjectSet = subjectSet;
}

public int getTeacherId() {
	return teacherId;
}

public void setTeacherId(int teacherId) {
	this.teacherId = teacherId;
}

public String getTeacherName() {
	return teacherName;
}

public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}

public List getClasSet() {
	return clasSet;
}

public void setClasSet(List clasSet) {
	this.clasSet = clasSet;
}

public List<Subject> getSubjectSet() {
	return subjectSet;
}

public void setSubjectSet(List<Subject> subjectSet) {
	this.subjectSet = subjectSet;
}


   



  
}
