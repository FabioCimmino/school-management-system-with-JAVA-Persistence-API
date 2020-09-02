package model.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exam {

    @Id 
    @GeneratedValue( strategy=GenerationType.AUTO )
    
    private int examId;
    private String examDate;
    private int examVote;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private Subject subject;
    
    public Exam () {}

	public Exam( String examDate, int examVote, Subject subject) {
		super();
		this.examDate = examDate;
		this.examVote = examVote;
		this.subject = subject;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public int getExamVote() {
		return examVote;
	}

	public void setExamVote(int examVote) {
		this.examVote = examVote;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}


    

    

}
