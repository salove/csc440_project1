package db;

import common.*;
import exception.RecordNotFoundException;


public interface AnswerAttempts extends Tables {
    void addAnswerAttempt(AnswerAttempt answerAttempt);
    AnswerAttempt getAnswerAttemptForExerciseAttemptAndQuestion(ExerciseAttempt exerciseAttempt, Question question) throws RecordNotFoundException;
}
