package db;

import java.util.List;

import common.Exercise;
import common.ExerciseAttempt;
import common.Student;

public class DBExerciseAttempts implements ExerciseAttempts {

    @SuppressWarnings("unused")
    private Connection connection;
    private DBExerciseAttempts(Connection connection) {
        this.connection=connection;
    }
    
    public static ExerciseAttempts getInstance(Connection connection) {
        return new DBExerciseAttempts(connection);
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
    public void addExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ExerciseAttempt> getExerciseAttemptsForStudent(Student s) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e) {
        // TODO Auto-generated method stub
        return null;
    }

}
