package db;

import common.*;


public interface AnswerAttempts extends Tables {
    void addAnswerAttempt(AnswerAttempt answerAttempt);
    AnswerAttempt getAnswerAttemptForExerciseAttemptAndQuestion(ExerciseAttempt exerciseAttempt, Question question);
}
