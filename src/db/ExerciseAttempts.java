package db;

import java.sql.SQLException;
import java.util.List;

import common.Exercise;
import common.ExerciseAttempt;
import common.Student;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public interface ExerciseAttempts extends Tables {
    void addExerciseAttempt(ExerciseAttempt exerciseAttempt);
    List<ExerciseAttempt> getExerciseAttemptsForStudent(Student s);
    List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException;
}
