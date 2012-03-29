package db;

import common.AnswerAttempt;
import common.ExerciseAttempt;
import common.Question;

public class DBAnswerAttempts implements AnswerAttempts {
    
    public static AnswerAttempts getInstance(Connection connection) {
        return new DBAnswerAttempts(connection);
    }
    
    @SuppressWarnings("unused")
    private Connection connection;
    private DBAnswerAttempts(Connection connection) {
        this.connection=connection;
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
    public void addAnswerAttempt(AnswerAttempt answerAttempt) {
        // TODO Auto-generated method stub

    }

    @Override
    public AnswerAttempt getAnswerAttemptForExerciseAttemptAndQuestion(
            ExerciseAttempt exerciseAttempt, Question question)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
