package db;

import java.util.ArrayList;
import java.util.List;

import common.Course;
import common.Exercise;
import exception.RecordNotFoundException;

public class UTExercises implements Exercises {

    private static UTExercises instance=null;
    public static Exercises getInstance(Connection connection) {
        if (null==instance) {
            instance=new UTExercises();
        }
        return instance;
    }
    
    ArrayList<Exercise> list=new ArrayList<Exercise>();
    private UTExercises() {
        
    }
    
    @Override
    public void dropTables() {
        list.clear();

    }

    @Override
    public void createTables() {
        // Nothing to do for UT

    }

    @Override
    public Exercise getExercise(Course course, String id) throws RecordNotFoundException {
        for (Exercise e:list) {
            if (e.getCourse().getKey().equals(course)) {
                if(e.getId().equals(id)) {
                    return e;
                }
            }
        }
        
        throw new RecordNotFoundException("Exercise "+id+" for course "+course.getToken()+" not found.");
    }

    @Override
    public List<Exercise> getExercisesByCourse(Course course) throws RecordNotFoundException {
        ArrayList<Exercise> retVal=new ArrayList<Exercise>();
        
        for (Exercise e:list) {
            if (e.getCourse().getKey().equals(course)) {
                retVal.add(e);
            }
        }
        
        if (0==retVal.size()) {
            throw new RecordNotFoundException("No exercises for course "+course.getToken()+" were found.");
        } 
        
        return retVal;
    }

    @Override
    public void putExercise(Exercise exercise) {
       list.add(exercise);

    }

}
