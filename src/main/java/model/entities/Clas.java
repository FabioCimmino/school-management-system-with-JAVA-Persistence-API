package model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Clas {

   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )
   
   private int classId;
   private String className;

   @ManyToMany(targetEntity=Teacher.class,cascade={CascadeType.MERGE})
   private List teacherSet;

   public Clas(){
      super();
   }

public Clas(String className, List teacherSet) {
	super();
	this.className = className;
	this.teacherSet = teacherSet;
}

public int getClassId() {
	return classId;
}

public void setClassId(int classId) {
	this.classId = classId;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

public List getTeacherSet() {
	return teacherSet;
}

public void setTeacherSet(List teacherSet) {
	this.teacherSet = teacherSet;
}



   
   
}
