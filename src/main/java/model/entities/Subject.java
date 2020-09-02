package model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subject {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO ) 

	private int subjectId;
	private String subjectName;
	
	   @ManyToMany(targetEntity = Teacher.class,cascade={CascadeType.MERGE})
	   private List<Teacher> teacherSet;
	
	public Subject() {
		super();
	}

	public Subject(String subjectName, List teacherSet) {
		super();
		this.subjectName = subjectName;
		this.teacherSet = teacherSet;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<Teacher> getTeacherSet() {
		return teacherSet;
	}

	public void setTeacherSet(List<Teacher> teacherSet) {
		this.teacherSet = teacherSet;
	}

	


}
