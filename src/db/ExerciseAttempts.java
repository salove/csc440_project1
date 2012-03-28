package db;

import java.util.List;

import common.Exercise;
import common.ExerciseAttempt;
import common.Student;

public interface ExerciseAttempts extends Tables {
    void addExerciseAttempt(ExerciseAttempt exerciseAttempt);
    List<ExerciseAttempt> getExerciseAttemptsForStudent(Student s);
    List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e);
}
