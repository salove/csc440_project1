package db;

import java.util.ArrayList;
import java.util.List;

import common.AnswerAttempt;
import common.ExerciseAttempt;
import common.Question;

public class UTAnswerAttempts implements AnswerAttempts {

    private List<AnswerAttempt> list = new ArrayList<AnswerAttempt>();
    
    private static UTAnswerAttempts instance=null;
    private UTAnswerAttempts() {
        
    }
    
    public static AnswerAttempts getInstance(Connection connection) {
        if (null==instance) {
            instance=new UTAnswerAttempts();
        }
        return instance;
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
