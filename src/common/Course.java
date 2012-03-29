package common;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private String token;
	private C_Date startDate;
	private C_Date endDate;
	Instructor instructor;
	ArrayList<TA> taList;
	ArrayList<Student> studentList;
	CourseSubject subject;
	Key key;
	
	Course(String token, CourseSubject courseSubject) {
		this.token=token;
		this.subject=courseSubject;
		key=new Key(token);
	}
	
	public Key getKey() {
		return key;
	}
	
	public void setStartDate(int month, int date, int year) {
		this.startDate=new C_Date(month,date,year);
	}
	
	public void setEndDate(int month, int date, int year) {
		this.endDate=new C_Date(month,date,year);
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor=instructor;
	}
	
	public void addTA(TA ta) {
		if (null==this.taList) {
			this.taList=new ArrayList<TA>();
		}
		this.taList.add(ta);
	}
	
	public void addStudent(Student student) {
		if (null==this.studentList) {
			this.studentList=new ArrayList<Student>();
		}
		this.studentList.add(student);
	}
	
	
	public String getToken() {
		return this.token;
	}
	
	public String getName() {
		return subject.getName();
	}
	
	public C_Date getStartDate() {
		return this.startDate;
	}
	
	public C_Date getEndDate() {
		return this.endDate;
	}
	
	public Instructor getInstructor() {
		return this.instructor;
	}
	
	public List<TA> getTAList() {
		return taList;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}
	
	public List<Topic> getTopicList() {
		return subject.getTopics();
	}
	
	
}
