import java.sql.SQLException;

import common.*;

import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;
import ui.UI;


public class Assessment {

    /**
     * @param args
     * @throws RoleException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws Exception {
        
        User a=new User("admin","Administrator",User.ROLE_ADMINISTRATOR, Utils.pwHash("pw"));
        User b=new User("i", "Instructor", User.ROLE_INSTRUCTOR, Utils.pwHash("pw"));
        User c=new User("ta1", "TA-1", User.ROLE_TA | User.ROLE_STUDENT, Utils.pwHash("pw"));
        User d=new User("ta2", "TA-2", User.ROLE_TA | User.ROLE_STUDENT, Utils.pwHash("pw"));
        
        CourseSubject mat=new CourseSubject("MAT");
        mat.setName("Math");
        CourseSubject bio=new CourseSubject("BIO");
        bio.setName("Biology");
        
        Course m101=new Course("601-spr12");
        m101.setSubject(mat);
        m101.setEndDate(12,21,2012);
        m101.setStartDate(9,7,2012);
        m101.addStudent(new Student(d));
        m101.addTA(new TA(c,m101.getToken()));
        m101.setInstructor(new Instructor(b));
        
        
        
        UI ui=new UI();
        try {
            Factory.getInstance(true).getUsers().putUser(a);
            Factory.getInstance(true).getUsers().putUser(b);
            Factory.getInstance(true).getUsers().putUser(c);
            Factory.getInstance(true).getUsers().putUser(d);
            Factory.getInstance(true).getCourseSubjects().putSubject(mat);
            Factory.getInstance(true).getCourseSubjects().putSubject(bio);
            Factory.getInstance(true).getCourses().putCourse(m101);
            
        } catch (RecordNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConnectionFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        ui.init();

    }
    
    

}
