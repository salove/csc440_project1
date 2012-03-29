package db;

import java.util.List;

import common.Course;
import common.Exercise;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class DBExercises implements Exercises {

    
    @SuppressWarnings("unused")
    private Connection connection;
    private DBExercises(Connection connection) {
        this.connection=connection;
    }
    
    public static Exercises getInstance(Connection connection) {
        return new DBExercises(connection);
    }
    
    @Override
    public void dropTables() {
        // TODO Auto-generated method stub

    }

    @Override
    public void createTables() {
        // TODO Auto-generated method stub

    }

    @Override
    public Exercise getExercise(Course course, String id)
            throws ConnectionFailedException, RecordNotFoundException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Exercise> getExercisesByCourse(Course course)
            throws RecordNotFoundException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putExercise(Exercise exercise) throws ConnectionFailedException
    {
        // TODO Auto-generated method stub

    }

}
