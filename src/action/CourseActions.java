package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class CourseActions {
    private Factory factory;
    
    public CourseActions(Session session) throws ConnectionFailedException {
        this.factory = Factory.getInstance(Settings.checkUnitTest());
    }
    
    public List<Course> getCoursesByInstructor(Instructor i) throws ConnectionFailedException, RecordNotFoundException, SQLException, RoleException {
        return factory.getCourses().getByInstructor(i);
    }
    
    public List<CourseSubject> getCourseSubjectsForInstructor(Instructor i) throws ConnectionFailedException, RecordNotFoundException, SQLException, RoleException {
        ArrayList<CourseSubject> subjects = new ArrayList<CourseSubject>();
        
        List<Course> courses= getCoursesByInstructor(i);
        
        for (Course c:courses) {
            if (!subjects.contains(c.getSubject())) {
               subjects.add(c.getSubject());
            }
        }
        
        return subjects;
    }
    
    public void addCourseSubject(CourseSubject courseSubject) throws ConnectionFailedException {
        factory.getCourseSubjects().putSubject(courseSubject);
    }
    
    public List<CourseSubject> getAllCourseSubjects() throws RecordNotFoundException, ConnectionFailedException {
        return factory.getCourseSubjects().getAll();
    }
    
    public List<Course> getAllCourses() throws RecordNotFoundException, ConnectionFailedException, SQLException {
        return factory.getCourses().getAll();
    }
    
    public void addCourse(String token, String idCode,
    		C_Date startDate, C_Date endDate, 
    		Instructor instructor, List<TA> taList ) throws ConnectionFailedException, SQLException {
    	Course course=new Course(token);
    	CourseSubject subject=getCourse(idCode);
    	course.setSubject(subject);
    	course.setStartDate(startDate);
    	course.setEndDate(endDate);
    	course.setInstructor(instructor);
    	for (TA ta:taList) {
    		course.addTA(ta);
    	}
    	
    	
    	factory.getCourses().putCourse(course);
    }

	private CourseSubject getCourse(String idCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addTopic(CourseSubject subject, String id, String name) throws ConnectionFailedException {
		Topic t=new Topic(subject,id);
		t.setName(name);
		
		
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		f.getTopics().addTopic(t);
		
		
	}
	
	public List<Topic> getTopics(CourseSubject subject) throws ConnectionFailedException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		return f.getTopics().getTopics(subject);
	}
	
	public void enrollStudent(Course course, Student student) throws ConnectionFailedException, RecordNotFoundException, SQLException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		f.getCourses().enrollStudent(course, student);
	}
	
}
