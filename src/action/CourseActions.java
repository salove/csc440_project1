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
}
